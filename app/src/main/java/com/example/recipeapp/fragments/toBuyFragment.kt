package com.example.recipeapp.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.database.ProductRepository
import com.example.recipeapp.model.Product
import com.example.recipeapp.ui.ProductAdapter
import kotlinx.android.synthetic.main.activity_to_buy.*
import kotlinx.android.synthetic.main.fragment_to_buy.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass.
 */
class toBuyFragment : Fragment() {

    private val shoppingList = arrayListOf<Product>()
    private lateinit var productRepository: ProductRepository
    private val productAdapter = ProductAdapter(shoppingList)
    private lateinit var thisContext: Context
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.recipeapp.R.layout.activity_to_buy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        thisContext = activity!!.applicationContext
        productRepository = ProductRepository(thisContext)
        initViews()
    }

    private fun initViews() {
        rvShoppingList.layoutManager = LinearLayoutManager(thisContext, RecyclerView.VERTICAL, false)
        rvShoppingList.adapter = productAdapter
        rvShoppingList.addItemDecoration(DividerItemDecoration(thisContext, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvShoppingList)
        getShoppingListFromDatabase()

        fab.setOnClickListener { addProduct() }
    }

    private fun validateFields(): Boolean {
        return if (etToBuy.text.toString().isNotBlank() && etAmountToBuy.text.toString().isNotBlank()) {
            true
        } else {
            Toast.makeText(thisContext, "Please fill in the fields", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun addProduct() {
        if (validateFields()) {
            mainScope.launch {
                val product = Product(
                    name = etToBuy.text.toString(),
                    quantity = etAmountToBuy.text.toString()
                )

                withContext(Dispatchers.IO) {
                    productRepository.insertProduct(product)
                }

                getShoppingListFromDatabase()
            }
        }
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val productToDelete = shoppingList[position]
                mainScope.launch {
                    withContext(Dispatchers.IO) {
                        productRepository.deleteProduct(productToDelete)
                    }
                    getShoppingListFromDatabase()
                }
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun getShoppingListFromDatabase() {
        mainScope.launch {
            val shoppingList = withContext(Dispatchers.IO) {
                productRepository.getAllProducts()
            }
            this@toBuyFragment.shoppingList.clear()
            this@toBuyFragment.shoppingList.addAll(shoppingList)
            this@toBuyFragment.productAdapter.notifyDataSetChanged()
        }
    }

}
