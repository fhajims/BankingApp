package at.fh.js.ims.banking

class CategoryList : Iterable<Category> {

    val CategoryOneID : UInt = 1.toUInt()
    val CategoryTwoID : UInt = 2.toUInt()
    val CategoryThreeID : UInt = 3.toUInt()
    val CategoryFourID : UInt = 4.toUInt()
    val CategoryFiveID : UInt = 5.toUInt()
    val CategorySixID : UInt = 6.toUInt()
    val CategorySevenID : UInt = 7.toUInt()

    var themutableList : MutableList<String> = mutableListOf()

    private var categories: MutableList<Category> = mutableListOf(Category(CategoryOneID, "Essen", "Du bist, was du isst"),
        Category(CategoryTwoID, "Hygiene", "waschiwasch"),
        Category(CategoryThreeID, "Miete", "miete"),
        Category(CategoryFourID, "Ertraege aus Kapitaleinlagen", "Alles, ........"),
        Category(CategoryFiveID, "Ertraege aus Kapitaleinlagen", "Alles, ........"),
        Category(CategorySixID, "Sonstiges", ""),
        Category(CategorySevenID, "Weißned", ""))


    fun addCategory(category: Category) {
        categories.add(category)
    }

    fun removeCategory(category: Category) {
        categories.remove(category)
    }

    fun addCategoryList(categoryList: List<Category>) {
        for (i in categoryList) {
            categories.add(i)
        }

        }

    override fun iterator(): Iterator<Category> {
        return categories.iterator()
    }

    fun getAllCategories(): List<Category> {
        return categories.toList()






    }



}
