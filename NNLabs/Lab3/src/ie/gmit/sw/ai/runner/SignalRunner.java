package ie.gmit.sw.ai.runner;

import ie.gmit.sw.ai.nn.BackpropagationTrainer;
import ie.gmit.sw.ai.nn.NeuralNetwork;
import ie.gmit.sw.ai.nn.Utils;
import ie.gmit.sw.ai.nn.activator.Activator;

public class SignalRunner {

	double[][] data = { { 1, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 1, 0, 1, 0 }, { 1, 0, 0, 0 }, { 0, 1, 0, 0 },
			{ 0, 0, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 0, 1 }, { 0, 1, 1, 1 }, { 1, 0, 1, 1 }, { 1, 0, 0, 1 },
			{ 0, 1, 0, 1 }, { 0, 0, 1, 1 } };

	double expected[][] = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 } };

	public SignalRunner() throws Exception {
		NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 4, 6, 14);
		BackpropagationTrainer trainer = new BackpropagationTrainer(nn);
		trainer.train(data, expected, 0.2, 500);

		double[] test = { 1, 1, 0, 1 };
		double[] result = nn.process(test);

		System.out.println(Utils.getMaxIndex(result) + 1);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new SignalRunner();

	}

}
