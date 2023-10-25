    package at.fh.js.ims.banking

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.text.Editable
    import android.text.InputFilter
    import android.text.TextWatcher
    import android.view.View
    import android.view.inputmethod.EditorInfo
    import android.widget.ArrayAdapter
    import android.widget.Button
    import android.widget.ListView
    import android.widget.TextView
    import android.widget.Toast
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.delay
    import kotlinx.coroutines.launch
    import android.util.Log
    import android.widget.LinearLayout
    import com.google.android.material.textfield.TextInputEditText
    import com.google.android.material.textfield.TextInputLayout
    import android.app.Activity
    import android.app.AlertDialog
    import android.content.Context

    import android.view.LayoutInflater
    import android.view.animation.AnimationUtils
    import android.view.inputmethod.InputMethodManager
    import android.widget.ImageView
    import androidx.core.content.ContentProviderCompat.requireContext
    import androidx.fragment.app.DialogFragment
    import java.util.Timer
    import java.util.TimerTask
    import at.fh.js.ims.banking.MyFunctions
    import android.media.MediaPlayer


    fun hideKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity.currentFocus
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(activity.currentFocus, InputMethodManager.SHOW_IMPLICIT)
    }




    class MainActivity : AppCompatActivity() {
        val CategoryOneID: UInt = 1.toUInt()
        val CategoryTwoID: UInt = 2.toUInt()
        val CategoryThreeID: UInt = 3.toUInt()
        val CategoryFourID: UInt = 4.toUInt()
        val CategoryFiveID: UInt = 5.toUInt()
        val CategorySixID: UInt = 6.toUInt()
        val CategorySevenID: UInt = 7.toUInt()

        val delayMillis: Long = 2000
        val listOfAllCategories: CategoryList = CategoryList()
        var stringArray: Array<String> = arrayOf()
        val ExpensesList: MutableList<Double> = mutableListOf()
        val IncomeList: MutableList<Double> = mutableListOf()

        val listOfNoemptyInputDialogFragments : MutableList<DialogFragment> = mutableListOf()
        val listOfSavingDialogFragments  : MutableList<DialogFragment> = mutableListOf()
        val listOfExpensesDialogFragments  : MutableList<DialogFragment> = mutableListOf()





        /*    mutableListOf(
            Category(CategoryOneID, "Essen", "Du bist, was du isst"),
            Category(CategoryTwoID, "Hygiene", "waschiwasch"),
            Category(CategoryThreeID, "Miete", "miete"),
            Category(CategoryFourID, "Ertraege aus Kapitaleinlagen", "Alles, ........"),
            Category(CategoryFiveID, "Ertraege aus Kapitaleinlagen", "Alles, ........"),
            Category(CategorySixID, "", ""),
            Category(CategorySevenID, "", "")
        )
    */
        val maxi = Person("maxi", "maxowitsch", 0.0, 0.0)

        //val categories : List<Category> = listOf(Category(CategoryOneID,"Essen", "Essma was Guads"))
        private var totalIncome = maxi.totalIncome
        private var totalExpenses = maxi.totalExpenses
        private lateinit var adapter: ArrayAdapter<String>

        // Here it all starts

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            //Fill the empty inputsList for DialogFragments
            listOfNoemptyInputDialogFragments.add(NoEmptyInputs())

            //Fill the Expenses list for DialogFragments
            listOfExpensesDialogFragments.add(BadFacey())

            //Fill Smileygoodboy list for DialogFragments
            listOfSavingDialogFragments.add(DialogFragment())

            val imageViewSmiley = findViewById<ImageView>(R.id.imageView)
            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            val idForContainer: LinearLayout = findViewById<LinearLayout>(R.id.idForContainer)
            val message = "A category has been added"
            val caemessage = "Category already exists"
            val duration = Toast.LENGTH_SHORT
            val context = applicationContext
            val toast = Toast.makeText(context, message, duration)
            val categoryAlreadyExists: Toast = Toast.makeText(context, caemessage, duration)
            val textForIncome: TextView = findViewById<TextView>(R.id.incomeText)
            val textForExpense: TextView = findViewById<TextView>(R.id.expenseText)
            val addIncomeButton: Button = findViewById(R.id.addIncomeButton)
            val addExpenseButton: Button = findViewById(R.id.addExpenseButton)
            //val editTextButton: EditText = findViewById(R.id.editText)
            var valueinForm: Double = 0.0
            val textyInputty: TextInputLayout = findViewById(R.id.textyinputty)
            //TextInputEditText
            val editThisText: TextInputEditText = findViewById(R.id.editThisText)
            val FormforInputandExpense: TextInputEditText =
                findViewById(R.id.TextInputIncomeandExpense)
            //addCategoryButton
            val addCategoryButton = findViewById<Button>(R.id.addCategoryButton)
            //ListView
            val categoryListView = findViewById<ListView>(R.id.categoryListView)
            categoryListView.visibility = View.GONE
            val headerView = layoutInflater.inflate(R.layout.popup_layout, null, false)
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
            categoryListView.adapter = adapter

            // Function to turn the visibility of all UI Elements off and display the list because otherwise
            // it would be transparent and you would see the background. Also visibility of ListView is toggled
            // because it sets the other UI elements out of focus when it is visible.





                addCategoryButton.setOnClickListener {
                hideKeyboard(this)
                addCategoryButton.visibility = View.GONE
                addExpenseButton.visibility = View.GONE
                addCategoryButton.visibility = View.GONE
                //editTextButton.visibility = View.GONE
                addIncomeButton.visibility = View.GONE
                //   textForIncome.visibility = View.GONE
                //  textForExpense.visibility = View.GONE
                textyInputty.visibility = View.GONE
                idForContainer.visibility = View.GONE
                FormforInputandExpense.visibility = View.GONE




                categoryListView.visibility = View.VISIBLE

                var addable: Boolean = true
                val value = editThisText.text.toString().trim()
                for (category in listOfAllCategories.getAllCategories()) {
                    if (category.description == value) {
                        addable = false

                    }

                    { }
                }
                if (value != "" && addable) {
                    listOfAllCategories.addCategory(
                        Category(
                            9.toUInt(),
                            editThisText.text.toString().trim(),
                            "s"

                        )
                    )
                    updateListView()
                    toast.show()



                } else {
                    hideKeyboard(this)
                    categoryAlreadyExists.show()


                }
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                            showKeyboard(this@MainActivity)
                        }
                    }
                }, 2000) // 2000 milliseconds (2 seconds)






                Log.d("MyTag", "myVariable = $listOfAllCategories")
                GlobalScope.launch(Dispatchers.Main) {
                    delay(delayMillis)
                    // Code to run after the delay

                    categoryListView.visibility = View.GONE
                    addCategoryButton.visibility = View.VISIBLE
                    addExpenseButton.visibility = View.VISIBLE
                    addCategoryButton.visibility = View.VISIBLE
                    //editTextButton.visibility = View.VISIBLE
                    addIncomeButton.visibility = View.VISIBLE
                    //    textForIncome.visibility = View.VISIBLE
                    //    textForExpense.visibility = View.VISIBLE
                    //editTextButton.visibility = View.VISIBLE
                    textyInputty.visibility = View.VISIBLE
                    idForContainer.visibility = View.VISIBLE
                    FormforInputandExpense.visibility = View.VISIBLE




                }







            }




                // Income Button, where the Income is added
                addIncomeButton.setOnClickListener {
                    processIncomeInput(FormforInputandExpense)




    }
        // Expense Button where the Expense is added
        addExpenseButton.setOnClickListener {
            MyFunctions.playNo(this@MainActivity)
            // make Boolean to prevent DialogFragment from appearing always.
            var expensesEmptyInput : Boolean = false

            try {

                val ExpenseInput = FormforInputandExpense.text.toString()
                if (ExpenseInput.isEmpty()) {
                    throw EmtpyExpensesInputException()
                }
                if (ExpenseInput.toDouble() == 0.0) {
                    // if (valueinForm == 0.0) {
                    totalExpenses += 0.0
                } else {
                    totalExpenses += ExpenseInput.toDouble()
                    updateIncomeAndExpenses()
                }
                ExpensesList.add(ExpenseInput.toDouble())
            } catch (e: EmtpyExpensesInputException) {
                Toast.makeText(this, "Expenses input cannot be empty", Toast.LENGTH_SHORT).show()
                // Set the Boolean to false to prevent Dialog from showing up when input is empty
                expensesEmptyInput = true
                val dialog = NoEmptyInputs()

                dialog.show(supportFragmentManager, "NoemptyInputs")
                GlobalScope.launch(Dispatchers.Main) {
                    delay(2000)
                    dialog.dismiss()
                }



            }
            //Hide the Keyboard Layout
            hideKeyboard(this)
            //Make Some notice for the user
            if (expensesEmptyInput == false) {
                Toast.makeText(this, "Expense has been added", Toast.LENGTH_SHORT).show()
                val dialog = BadFacey()
                dialog.show(supportFragmentManager, "BadFacey")
                GlobalScope.launch(Dispatchers.Main) {
                    delay(2000)
                    dialog.dismiss()
                }
            }
                //Make some delay
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        showKeyboard(this@MainActivity)
                    }
                }, 2000) // 2000 milliseconds (2 seconds)

            //Show the dialog of disappointment

        }
        // Some stupid s... Action done Listener

        FormforInputandExpense.setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
        valueinForm = FormforInputandExpense.text.toString().toDouble()}
        // Handle the action (e.g., "EditorInfo.IME_ACTION_DONE")
        // You can also access the text using editText.text.toString()
        return@setOnEditorActionListener true

            }

        //Some Filter

            val filter = InputFilter { source, start, end, _, _,_ ->
                for (i in start until end) {
                    if (!Character.isDigit(source[i])) {
                        return@InputFilter ""
                    }
                }
                null
            }



            FormforInputandExpense.filters = arrayOf(filter)

            // Text change listener
            FormforInputandExpense.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var a = s
                }

                override fun afterTextChanged(editable: Editable?) {
                    val input = editable.toString()
                    val numericInput = input.replace("[^0-9.]".toRegex(), "")

                    // Update the EditText with the numeric input
                    if (editable.toString() != numericInput) {
                        FormforInputandExpense.setText(numericInput)
                        FormforInputandExpense.setSelection(numericInput.length)
                    }
                    FormforInputandExpense.hint = input

                }





                })
        }
                // Function to update the text Views

                private fun updateIncomeAndExpenses() {
                    val incomeText: TextView = findViewById(R.id.incomeText)
                    val expenseText: TextView = findViewById(R.id.expenseText)
          //          val incomeInput = incomeText.text.toString()
          //          val expenseInput = expenseText.text.toString()
     /*               if (incomeInput.isNotBlank()) {
                        val income = incomeInput.toDoubleOrNull() ?: 0.0 // Convert income input to a Double
                        totalIncome += income
                        IncomeList.add(income)
                    // Update the income TextView
                    }
                    if (expenseInput.isNotBlank()) {
                        val expense = incomeInput.toDoubleOrNull() ?: 0.0 // Convert income input to a Double
                        totalIncome += expense
                        ExpensesList.add(expense)
                        // Update the expense TextView
                    }  */
                    incomeText.text = "Income: \$$totalIncome"
                    expenseText.text = "Expenses: \$$totalExpenses"
                }
        private fun processIncomeInput(FormforInputandExpense : TextInputEditText) {

            var incomeEmptyInput: Boolean = false

            try {     // Trying

                var incomeInput = FormforInputandExpense.text.toString()
                // Test, if input is "" or some bs
                if (incomeInput.isEmpty()) {
                    throw EmptyIncomeInputException()
                }
                if (incomeInput.toDouble() == 0.0) {
                    // if (valueinForm == 0.0) {
                    totalIncome += 0.0

                } else {
                    totalIncome += incomeInput.toDouble()
                    updateIncomeAndExpenses()
                    IncomeList.add(incomeInput.toDouble())
                }
            } catch (e: EmptyIncomeInputException) {
                Toast.makeText(this, "Income input cannot be empty", Toast.LENGTH_SHORT)
                    .show()

                incomeEmptyInput = true
                val dialog = NoEmptyInputs()
                dialog.show(supportFragmentManager, "NoemptyInputs")

                GlobalScope.launch(Dispatchers.Main) {
                    delay(2000)
                    dialog.dismiss()
                }
            }
            hideKeyboard(this)


            //Show the smiley :)
            if (incomeEmptyInput == false) {
                Toast.makeText(this, "Income has been added", Toast.LENGTH_SHORT).show()
                MyFunctions.playSkibididopdop(this)
                val dialog = CustomDialogFragment()
                dialog.show(supportFragmentManager, "CustomDialogFragment")
                GlobalScope.launch(Dispatchers.Main) {
                    delay(2000)
                    dialog.dismiss()
                }
            }
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    showKeyboard(this@MainActivity)
                }
            }, 2000) // 2000 milliseconds (2 seconds)

        }



        //Completeley unnecessary function

        fun getContent(s: CharSequence) {
            if (s.isNotEmpty()) {
                val valueInForm = s.toString().toDouble()
                totalIncome += valueInForm
                updateIncomeAndExpenses()
                IncomeList.add(valueInForm)
            }
        }

        // Update list view of Categories

        private fun updateListView() {
            adapter.clear()
            for (category in listOfAllCategories.getAllCategories()) {
                adapter.add(category.description)
            }

        //Function to get categories in Array of Strings, i don´t think it is being used.

            fun getAllCategoriesInStringArray(categoryList: CategoryList): List<String> {
                val stringList = mutableListOf<String>()
                for (category in categoryList.getAllCategories()) {
                    stringList.add(category.toString()) // You might want to customize this to get the string representation you need.
                }
                return stringList
            }













        }

    }

