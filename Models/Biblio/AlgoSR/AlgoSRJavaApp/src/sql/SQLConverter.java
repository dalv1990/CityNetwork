/**
 * 
 */
package sql;

import java.util.HashSet;

import utils.CSVWriter;
import utils.GEXFWriter;
import main.Main;
import main.corpuses.Corpus;
import main.reference.Reference;

/**
 * @author Raimbault Juste <br/> <a href="mailto:juste.raimbault@polytechnique.edu">juste.raimbault@polytechnique.edu</a>
 *
 */
public class SQLConverter {
	
	
	/**
	 * converts a simple cyb sql base to gexf.
	 * 
	 * @param database
	 * @param outfile
	 */
	public static void sqlToGexf(String database,String outfile){
		Corpus corpus = SQLImporter.sqlImport(database, "cybergeo", "refs", "links", false);
		GEXFWriter.writeCitationNetwork(outfile, corpus.references);
	}
	
	/**
	 * converts sql to csv (links and nodes)
	 * 
	 * @param database
	 * @param outPrefix
	 */
	public static void sqlToCsv(String database, String outPrefix){
		Corpus corpus = SQLImporter.sqlImport(database, "cybergeo", "refs", "links", false);
		
		HashSet<String[]> links = new HashSet<String[]>();
		HashSet<String[]> refs = new HashSet<String[]>();
		
		for(Reference r:corpus){
			String prim = "0";if(r.attributes.containsKey("primary")){prim="1";}
			String[] ref = {r.scholarID,r.title.title,r.year,prim}; 
			refs.add(ref);
			for(Reference rc:r.citing){String[] link = {rc.scholarID,r.scholarID};links.add(link);}
		}
		
		String[][] linksTab = new String[links.size()][2];
		String[][] refsTab = new String[refs.size()][4];
		
		int i = 0;for(String[] l:links){linksTab[i]=l;i++;}
		i = 0;for(String[] l:refs){refsTab[i]=l;i++;}
		
		CSVWriter.write(outPrefix+"_links.csv", linksTab, ";");
		CSVWriter.write(outPrefix+"_nodes.csv", refsTab, ";");
	}
	
	
	public static void main(String[] args){
		Main.setup();
		sqlToCsv("cybprov","res/nwcsv/provnw");
	}
	
	
	
}
