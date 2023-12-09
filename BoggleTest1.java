package ass4;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class BoggleTest1 {
	// the 16 Boggle dice (1992 version)
	private static final String[] BOGGLE_1992 = {
	        "LRYTTE", "VTHRWE", "EGHWNE", "SEOTIS",
	        "ANAEEG", "IDSYTT", "OATTOW", "MTOICU",
	        "AFPKFS", "XLDERI", "HCPOAS", "ENSIEU",
	        "YLDEVR", "ZNRNHL", "NMIQHU", "OBBAOJ"
	};

	// the 25 Big Boggle dice
	private static final String[] BOGGLE_BIG = {
	        "AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM",
	        "AEEGMU", "AEGMNN", "AFIRSY", "BJKQXZ", "CCENST",
	        "CEIILT", "CEILPT", "CEIPST", "DDHNOT", "DHHLOR",
	        "DHLNOR", "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU",
	        "FIPRSY", "GORRVW", "IPRRRY", "NOOTUW", "OOOTTU"
	};	
	
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
	
	@Test
	//Tests boggle dice shaking using a random seed (97)
	void testConstructorDice( ) {		
		Boggle b = new Boggle(BOGGLE_1992, 97);
		String [][] expectedBoard = {
				{"l","c","e","n"},
				{"o","n","b","r"},
				{"n","w","o","d"},
				{"i","t","m","s"} 
			};
		
		System.out.println(b);
		String [][] actualBoard = b.board;
		for (int i=0; i<expectedBoard.length; i++)
			for (int j=0; j<expectedBoard[0].length; j++)
				assertEquals(expectedBoard[i][j], actualBoard[i][j]);		
	}
	
	@Test
	//tests reading board from file
	void testConstructorFile() {
		String fileName = "board-points3.txt";
		String [][] expectedBoard = {
				{"h","s","y","j"},
				{"w","t","c","t"},
				{"t","f","g","h"},
				{"n","y","t","l"} 
			};
		Boggle b = new Boggle (fileName);
		//System.out.println(b);
		String [][] actualBoard = b.board;
		for (int i=0; i<expectedBoard.length; i++)
			for (int j=0; j<expectedBoard[0].length; j++)
				assertEquals(expectedBoard[i][j], actualBoard[i][j]);		
	}	
	
	@Test
	// tests generating all valid words given dictionary and the board files
	void testGenerateAll() {
		//test exhaustive search with a given board and given dictionary
    	String dictName = "dictionary.txt";
    	String boardName = "board.txt";
    	List<String> allWords = Boggle.getAllValidWords(dictName, boardName);
    	assertEquals(1351, allWords.size() );     	
    	
    	// one more board
    	boardName = "board-points361.txt";
    	allWords = Boggle.getAllValidWords(dictName, boardName);
    	assertEquals(361, allWords.size() );  
    	
    	// and the words themselves
    	boardName = "board-points3.txt";
    	allWords = Boggle.getAllValidWords(dictName, boardName);
    	assertEquals(3, allWords.size() );     	
    	assertEquals("[cyst, sty, tyg]",allWords.toString());
	}
	
	@Timeout(10)
	@Test
	void testGenerateAllTime() {
		assertTimeout(Duration.ofSeconds(10), () -> {			
			long start = System.currentTimeMillis();
			String dictName = "dictionary.txt";
	    	String boardName = "board.txt";
	    	Boggle.getAllValidWords(dictName, boardName);	    	
			long end = System.currentTimeMillis();
			System.out.println("DEBUG: Generate all time test run in: " + (end - start)/1000 + " sec");
	    });
	}
	
	@Test
	// tests that can find all test words from the corresponding boards
	void testSearch() {
		// correctness test of findWord
    	for (int k=0; k < boards.length; k++) {			
			String [][] board = boards[k];
			Boggle b = new Boggle (board);
			// System.out.println(b);
			
			String [] answersk = answers[k];
			for (int i=0; i< answersk.length; i++) {
				String word = answersk[i].toLowerCase();
				// replace the qu
				StringBuilder sb = new StringBuilder();
				for (int m = 0; m< word.length(); m++) {
					if (word.charAt(m)!='q')
						sb.append(word.charAt(m));
					else {
						sb.append('q');
						m++; //skip 'u'
					}						
				}
				word = sb.toString();
				
				assertTrue(b.matchWord(word));							
			}					
		}
	}
	
	@Test
	// this tests the marking of the board with the corresponding letter
	// and it also tests the reset after each word
	void testMark() {
		String [][] board = boards[0];
		Boggle b = new Boggle (board);
		assertTrue(b.matchWord("bee"));
		String expectedString = "h E B " + System.lineSeparator()
				+ "E z k "  + System.lineSeparator()
				+ "t s t "  + System.lineSeparator();
		
		assertEquals(expectedString, b.toString());
		
		// match another word - after cleaning markings of the previous word
		expectedString = "h e b " + System.lineSeparator()
		+ "E z k "  + System.lineSeparator()
		+ "T S T "  + System.lineSeparator();
		assertTrue(b.matchWord("test"));
		assertEquals(expectedString, b.toString());
		System.out.println(b);
	}	

}