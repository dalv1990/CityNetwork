/**
 * 
 */
package main;

/**
 * @author Raimbault Juste <br/> <a href="mailto:juste.raimbault@polytechnique.edu">juste.raimbault@polytechnique.edu</a>
 *
 */
public class Abstract {
	
	public String resume;
	
	/**
	 * if translated
	 */
	public String en_resume;
	
	public Abstract(String r){resume=r;}
	
	public Abstract(String r,String e){resume=r;en_resume=e;}
	
}
