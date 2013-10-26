/**
 * This Patron class defines the attributes of
 * a patron in a restaurant
 */
package com.raven.waiterhelper.db;

/**
 * @author Tendai T.T. Mudyiwa
 * @version October 24 2013
 */
public class Patron {

	private String seatNumber;
	private String tableID;
	private String mealOrdered;
	

	public Patron(String tableId, String seatNumber,String mealOrdered)	{
		this.tableID = tableId;
		this.seatNumber = seatNumber;
		this.mealOrdered = mealOrdered;
	}

	/**
	 * @return the seatNumber
	 */
	public String getSeatNumber() {
		return seatNumber;
	}

	/**
	 * @return the tableID
	 */
	public String getTableID() {
		return tableID;
	}

	/**
	 * @return the mealOrdered
	 */
	public String getMealOrdered() {
		return mealOrdered;
	}
	
	@Override
	public String toString(){
		String patronDetails = new String();
		patronDetails = "Table: "+ tableID +" " + '\n' +"Seat: " +seatNumber+" "+'\n' + "Meal: "+ mealOrdered +" "+'\n'+"****************"+'\n';
		return patronDetails;
		
	}

}
