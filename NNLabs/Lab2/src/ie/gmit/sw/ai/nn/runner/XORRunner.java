package ie.gmit.sw.ai.nn.runner;

import ie.gmit.sw.ai.nn.BackpropagationTrainer;
import ie.gmit.sw.ai.nn.NeuralNetwork;
import ie.gmit.sw.ai.nn.activator.Activator;

public class XORRunner {

	public XORRunner() throws Exception {
		// Get Training Data
		double[][] data = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } };
		double[][] expected = { { 0 }, { 1 }, { 1 }, { 0 } };
		// Configure the NN Topology
		NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 2, 2, 1);
		// Train NN
		BackpropagationTrainer trainer = new BackpropagationTrainer(nn);
		trainer.train(data, expected, 0.01, 1000000);
		// Test NN
		double[] test1 = { 0.0, 0.0 };
		double[] test2 = { 1.0, 0.0 };
		double[] test3 = { 0.0, 1.0 };
		double[] test4 = { 1.0, 1.0 };
		
		System.out.println("00=>" + getRoundedValue(nn.process(test1)));
		System.out.println("01=>" + getRoundedValue(nn.process(test2)));
		System.out.println("10=>" + getRoundedValue(nn.process(test3)));
		System.out.println("11=>" + getRoundedValue(nn.process(test4)));

	}

	public static long getRoundedValue(double[] vector) {
		return Math.round(vector[0]);
	}



	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new XORRunner();
	}

}
