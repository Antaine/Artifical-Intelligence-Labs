package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class DappingWithSugeno {
	// FCL File
	private static final String FCL_FILE = "./fcl/Dapping.fcl";

	public double getDappingLevel(int windBeaufort, int tempCelsius) {
		FIS fis = FIS.load(FCL_FILE, true);
		//Get FunctionBlock
		FunctionBlock fb = fis.getFunctionBlock("getDappingLevel");
		//Set Variables
		fis.setVariable("wind", windBeaufort);
		fis.setVariable("temperature", tempCelsius);
		//Calculate
		fis.evaluate();
		Variable dapping = fb.getVariable("dapping");
		//Display
		JFuzzyChart.get().chart(fis);
		JFuzzyChart.get().chart(dapping.getDefuzzifier(), "Dapping Level", true);
		//Return Defuzzified Value
		return dapping.getValue();}
	
	public static void main(String[] args) {
		DappingWithSugeno d = new DappingWithSugeno();
		double salary = d.getDappingLevel(7, 19);
		System.out.println(salary);

	}

}
