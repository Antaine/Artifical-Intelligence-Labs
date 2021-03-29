package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Staffing {
	private static final String FCL_FILE = "./fcl/Staffing.fcl";

	public double getRisk(double funding, int staffing) {
		//Get FCL FILE
		FIS fis = FIS.load(FCL_FILE, true);
		//Get FunctionBLock
		FunctionBlock fb = fis.getFunctionBlock("getRisk");
		//Display Chart
		JFuzzyChart.get().chart(fb);
		//Set Funding & Staffing
		fis.setVariable("funding", funding);
		fis.setVariable("staffing", staffing);
		//Run Fuzzy Logic
		fis.evaluate();
		//Store Result
		Variable risk = fb.getVariable("risk");
		
		JFuzzyChart.get().chart(risk, risk.getDefuzzifier(),true);
		
		//Output Result
		//System.out.println(fis.getVariable("risk").getValue());
		
		
		return (float) risk.getValue();
	}
	public static void main(String[] args) {
		
		Staffing s = new Staffing();
		double risk = s.getRisk(60, 14);
		System.out.println(risk);

	}

}
