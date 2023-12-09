// package ass4;

import java.util.*;

public class BoggleTest {
	public static final String [][] board0 = {
		{ "H","E","B"},
		{"E","Z","K"},
		{"T","S","T"} 
	};
	
	public static final String [][] board1 = {
		{"I","O","N"},
		{"T","E","V"},
		{"R","C","V"} 
	};
	
	public static final String [][] board2 = {
		{"P","R","D","E"},
		{"O","T","L","J"},
		{"E","I","E","A"},
		{"C","O","R","N"} 
	};
	
	public static final String [][] board3 = {
		{"U","G","I","A"},
		{"O","H","S","S"},
		{"T","U","E","T"},
		{"Y","N","T","W"} 
	};
	
	public static final String [][] board4 = {
		{"H","A","I","X"},
		{"R","E","O","O"},
		{"Y","Q","E","O"},
		{"H","R","F","O"} 
	};
	
	public static final String [][] board5 = {
		{"E","S","P","O","L"},
		{"I","W","A","D","O"},
		{"M","O","D","L","V"},
		{"A","S","T","L","P"},
		{"H","E","I","R","E"} 
	};
	
	public static final String[][][] boards = {
		board0,
		board1,
		board2,
		board3,
		board4,
		board5
	};
			
    public static final String[][] answers = {
	{ "bee","bees","beet","beets","behest","hes","hest","het","hets",
	  "keet","keets","see","seek","set","skee","skeet","steek","tee",
	  "test","tsk","zee","zees","zek","zeks","zest" },
	{ "envoi","eon","ion","net","not","note","noter","one","oven",
	  "over","overt","rec","recti","recto","rei","renvoi","ret",
	  "rev","ten","tie","tier","toe","ton","tone","toner","vert",
	  "vet","veto","voe","vote","voter" },
	{ "aerie","aero","alien","alit","alter","alto","anele","aneled",
	  "areic","arete","ariel","aril","ariled","ceil","ceiled","ceiler",
	  "ceorl","cete","cire","cite","citer","coeternal","coil","coiled",
	  "coiler","coir","coral","core","corn","cornea","corneal","cornel",
	  "cornet","dele","delete","deli","deltic","droit","drop","dropt",
	  "earn","elan","elite","eternal","etic","etoile","ilea","jane",
	  "jean","jete","lane","lari","lean","lear","learn","lice","lien",
	  "lier","lira","lire","lite","liter","naled","naric","near",
	  "netop","oiled","oiler","optic","oral","oriel","orle","otic",
	  "poet","poetic","port","porter","portico","portlier","protea",
	  "protean","protei","rale","real","relic","relit","renal","reoil",
	  "reoiled","rete","retie","retile","retiled","retro","rice","riel",
	  "rile","riled","riot","rite","roil","roiled","rote","roti","rotl",
	  "teal","tear","tela","tele","telic","tern","tier","tilde","tile",
	  "tiled","tiler","tire","tiro","toil","toile","toiled","toiler",
	  "trop" },
	{ "ashen","ashes","assent","asset","enthusiast","ghis","gist",
	  "gists","gout","gouty","hent","hest","hests","hets","hiss",
	  "hist","hists","hogs","hotness","house","houses","hues","hugs",
	  "hunt","issue","ness","nest","nests","nets","nett","netts",
	  "newt","newts","ohia","ohias","ought","oust","ousts","outwent",
	  "sash","sent","sets","sett","setts","shent","shes","shew",
	  "shist","shog","shot","shout","shun","shunt","shut","shute",
	  "shutes","sigh","sighs","sight","stet","stew","sues","suet",
	  "suets","tent","tenth","tenths","tenty","tenuto","test","tests",
	  "tets","then","thesis","thew","this","thou","thous","thug",
	  "thugs","thus","togs","tough","toughen","toughest","toughs","touse",
	  "touses","tout","tune","tunes","tush","tushes","tusseh","twenty",
	  "tyne","tynes","ughs","unto","uses","went","west","wests","wets" },
	{ "aery","eery","eyra","eyre","free","freer","fryer","hare","hear",
	  "queer","query","quey","reef","rhea","yeah","year" },
	{ "addle","adds","ados","aldol","aldose","allies","alto","altos",
	  "alts","amie","amies","amis","amosite","apod","apse","astir",
	  "awes","dado","dados","dads","dallies","daps","dawdle","dawdler",
	  "daws","doll","dollies","dollish","dolt","doltish","dolts","doms",
	  "dopa","dopas","dose","dost","dote","dotes","dots","dowie","dows",
	  "dowse","east","elds","elite","elites","haes","haet","haets","hams",
	  "hast","haste","heil","heir","heist","hest","hets","istle","ladle",
	  "ladler","lads","laps","lapse","lase","laws","lies","lire","list",
	  "lite","litre","lits","load","loads","loll","loller","lollies","loop",
	  "loops","lops","maes","maestri","mash","mast","misadd","misadds",
	  "misallies","mise","moas","modal","mods","most","moste","mote",
	  "motes","motile","mots","mows","oast","odds","olds","oops","opal",
	  "ostler","owes","owse","paddle","paddler","padle","pads","pall",
	  "palliest","palp","pase","paws","pelite","pelites","pelt","peltries",
	  "pelts","peri","peril","perilla","perillas","peris","perish",
	  "perlite","perlites","pert","plie","plies","plod","polo","pood",
	  "pool","pries","priest","prill","prise","prism","psaltries","relies",
	  "relish","relist","relit","replies","rile","rill","rise","rite",
	  "rites","saddle","saddler","saddleries","sall","sallies","salol",
	  "saloop","salp","salt","saltie","salties","saltire","saltish",
	  "salts","salvo","seam","sham","shamois","shea","sild","sill","silt",
	  "sima","simas","sims","sire","site","smote","soap","soaps","soda",
	  "sodas","soma","sows","spado","spall","spaller","spool","steam",
	  "stile","still","stir","stirp","stoa","stoae","stoas","stoma",
	  "stow","stowp","stowps","stows","strep","swaddle","swap","swim",
	  "swims","swot","swots","team","teams","teas","ties","tile","tiler",
	  "till","tire","tirl","toad","toads","tods","toms","tosh","towie",
	  "towies","tows","tries","trill","trisomies","volt","volte","voltes",
	  "volti","volts","waddle","waddler","wads","wadset","wall","wallie",
	  "wallies","waps","wasp","wise","wisp","woad","woads","woald",
	  "woalds","wost","wots" } 
	};
    
    public static void main (String [] args) {
    	
    	// correctness test of findWord
    	for (int k=0; k < BoggleTest.boards.length; k++) {			
			String [][] board = BoggleTest.boards[k];
			Boggle b = new Boggle (board);
			System.out.println(b);
			
			String [] answers = BoggleTest.answers[k];
			for (int i=0; i< answers.length; i++) {
				String word = answers[i].toUpperCase();
				// replace the qu
				StringBuilder sb = new StringBuilder();
				for (int m = 0; m< word.length(); m++) {
					if (word.charAt(m)!='Q')
						sb.append(word.charAt(m));
					else {
						sb.append('Q');
						m++; //skip 'u'
					}						
				}
				word = sb.toString();
				
				if(!b.matchWord(word)) {
					System.out.println("Could not find word " + answers[i] + " in this grid.");
					return;
				}				
			}
			System.out.println("All good\n");			
		}
    	
    	//test exhaustive search with a given board and given dictionary
    	String dictName = "dictionary.txt";
    	String boardName = "board.txt";
    	List<String> allWords = Boggle.getAllValidWords(dictName, boardName);
    	System.out.println("Found "+allWords.size() +" valid words using '"+ dictName
    			+ "' and '" + boardName +"'.");  //should be 1381 words found in less than 5 sec
    }

}
