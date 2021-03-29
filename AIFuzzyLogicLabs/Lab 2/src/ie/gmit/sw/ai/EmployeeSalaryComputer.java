package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class EmployeeSalaryComputer {

	private static final String FCL_FILE = "./fcl/Salary.fcl";
	
	public double getSalary(int iq, int qualification, int experience, float screen) {
		//Get FCL FILE
		FIS fis = FIS.load(FCL_FILE, true);
		//Get FunctionBLock
		FunctionBlock fb = fis.getFunctionBlock("getSalary");
		//Display Chart
		JFuzzyChart.get().chart(fb);
		//Set Variables
		fis.setVariable("iq", iq);
		fis.setVariable("qualification", qualification);
		fis.setVariable("experience", experience);
		fis.setVariable("screen", screen);
		//Run Fuzzy Logic
		fis.evaluate();
		//Store Result
		Variable salary = fb.getVariable("salary");
		//Display Data
		JFuzzyChart.get().chart(salary, salary.getDefuzzifier(),true);
		//Return Salary
		return (float) salary.getValue();
	}
	public static void main(String[] args) {
		EmployeeSalaryComputer e = new EmployeeSalaryComputer();
		double salary = e.getSalary(120, 8, 7 ,1);
		System.out.println(salary);
	}

}
