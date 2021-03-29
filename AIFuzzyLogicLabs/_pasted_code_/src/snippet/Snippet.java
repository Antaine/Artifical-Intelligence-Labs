package snippet;

public class Snippet {
	FUNCTION_BLOCK getSalary
	
		VAR_INPUT
			iq : REAL;
			qualification : REAL;
			experience : REAL;
			screen : REAL;
		END_VAR
		
		VAR_OUTPUT
			salary : REAL;
		END_VAR
		
		FUZZIFY iq
			TERM low := gbell 10 30 80;
			TERM average := gbell 10 30 100;
			TERM high := gbell 10 30 120;
		END_FUZZIFY
		
		FUZZIFY qualification
			TERM basic := (0,1)(2,1)(4,0);
			TERM moderate := trape 2 3 5 6;
			TERM excellent := (4,0)(6,1)(8,1);
		END_FUZZIFY
		
		FUZZIFY experience
			TERM junior := (0,1)(2,1) (3,0);
			TERM midlevel := trape 2 3 5 7;
			TERM senior := (4,0)(6,1) (8,1);
		END_FUZZIFY
		
		FUZZIFY screen
			TERM clueless := trian 0 2.5 5;
			TERM good := trian 2.5 5 7.5;
			TERM excellent := trian 5 7.5 10;
		END_FUZZIFY
		
		DEFUZZIFY salary
			TERM low := (0, 1) (15, 0);
			TERM average := trape 10 16 19 25;
			TERM high := (20, 0) (35, 1);
			METHOD : COG;
			//METHOD : MM;
			//METHOD : LM;
			//METHOD : RM;
			//METHOD : COGS;
			//METHOD : COGF;
			DEFAULT := 25;
		END_DEFUZZIFY
		
		RULEBLOCK No1
			AND : MIN;
			ACT : MIN;
			ACCU : MAX;
			
			RULE 1 : IF iq IS low AND qualification IS basic THEN salary IS low;
			RULE 2 : IF experience IS senior OR screen IS excellent THEN salary IS high;
			RULE 3 : IF qualification IS moderate AND screen IS good THEN salary IS average;
		END_RULEBLOCK
	
	END_FUNCTION_BLOCK
}
