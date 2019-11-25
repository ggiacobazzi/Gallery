package functionalities;

import java.util.ArrayList;

/**
 * Class used to create a photoalbum containing images and categories
 * @author gabbb
 *
 */

public class PhotoAlbum {
		private ArrayList<Category> categoryList;
		private ArrayList<Image> list;
		private int numberOfCategories;  //Can be removed, gonna figure it out later :)
		
		public PhotoAlbum() {
			setList();
			setNumberOfCategories(0);
		}

		public void addCategory(PhotoAlbum alb, Category cat) {
			alb.categoryList.add(cat);
			alb.numberOfCategories++;
		}
		public void removeCategory(PhotoAlbum alb, Category cat) {
			alb.categoryList.remove(cat);
			alb.numberOfCategories--;
		}
		
		//Remove all images and all categories
		public void removeAll(PhotoAlbum alb) {
			alb.categoryList.removeAll(categoryList);
			alb.list.removeAll(list);
			alb.numberOfCategories = 0;
		}
		
		public void setList() {
			this.list = new ArrayList<Image>();
		}
		
		public ArrayList<Image> getList(){
			return this.list;
		}
		
		public int getNumberOfCategories() {
			return numberOfCategories;
		}
		public void setNumberOfCategories(int numberOfCategories) {
			this.numberOfCategories = numberOfCategories;
		}
}
