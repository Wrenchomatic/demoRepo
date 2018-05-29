/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kompletering;

/**
 *
 * @author Antti
 */

public class Golfer {

	String namn;
	String forening;
        int slag = 0;
	int points = 0;
	boolean stillPlaying = true;

	//konstruktor for the user
	public Golfer (String indata) {			
		String [] in = indata.split(",");
		namn = in[0];
		forening = in[1];
                this.slag = 0;
		this.points = 0;
	}

	/**
	 * Getters
	 */
	
	public int getPoints() {
		return points;
	}

	public String getNamn() {
		return namn;
	}

	public String getForening() {
		return forening;
	}
	
	public String getInfo(){
		return "Deltagarens namn är " + namn + " föreningen är " + forening + " och poängen är " + points;
	}
	
	public boolean getState() {
		return stillPlaying;
	}
	
        public int getSlag() {
            return slag;
        }
	
/**
 * Setters
 */
	
	public void setState(boolean b) {
		stillPlaying = b;
	}

	public void setPoints(int i) {
		points = points + i;
		//return points;
	}
	
	public void setDNF(String dnf) {
		dnf = "dnf";
	}
        
        public void resetPoints() {
            this.points = 0;
        }
	
        public void addSlag(int i) {
            slag = slag +i;
        }
	
}