package functionalities;

import java.util.ArrayList;

/**
 * Class used to create an ArrayList of "Category" object in order 
 * to make things easier (adding/removing/renaming categories)
 * @author gabbb
 *
 */

public class Categories {
		private ArrayList<PhotoAlbum> list;
		private int numberOfCategories;  //Can be removed, gonna figure it out later :)
		
		public Categories() {
			setNumberOfCategories(0);
		}

		public void addCategory(Categories arr, PhotoAlbum cat) {
			arr.list.add(cat);
		}
		public void removeCategory(Categories arr, PhotoAlbum cat) {
			arr.list.remove(cat);
		}
		public void removeAll(Categories arr) {
			arr.list.removeAll(list);
		}
		public int getNumberOfCategories() {
			return numberOfCategories;
		}
		public void setNumberOfCategories(int numberOfCategories) {
			this.numberOfCategories = numberOfCategories;
		}
}
