package ie.gmit.sw.ai;

import java.util.*;
public class Perceptron {
	private float[] weights;
	private float theta = 0.2f;
	
	public Perceptron(int connection) {
		weights = new float[connection];
	    
		for (int i = 0; i < weights.length; i++) {
	      weights[i] = random.nextFloat() * 2 - 1;
		}

		System.out.println(this);
	}
	
	public float[] getWeights() {
		return weights; 
	}
	
	public int activate(float[] inputs) {
	    float sum = 0;
	    for (int i = 0; i < weights.length; i++) {
	      sum += inputs[i] * weights[i];
	    }
	    
	    return sum - theta >= 0 ? 1 : 0;
	    
	}
	
	public String toString() {
		return "Perceptron [weights=" + Arrays.toString(weights) + "]";
	}

	
	//Trainer...
	private float alpha = 0.1f; //Alpha learning rate
	private Random random = new Random();

	public void train(float[][] inputs, float[] expected, int max_epochs) {
		boolean hasError = true;
		float error = 1000;
		int epoch = 0;
		
		while (epoch < max_epochs && hasError){
			hasError = false;
			
			for (int row = 0; row < inputs.length; row++){
				int actual = activate(inputs[row]);
				error = expected[row] - actual;
				if (error != 0) hasError = true;

				//Adjust weights based on weightChange * input
				for (int i = 0; i < weights.length; i++) {
					weights[i] += alpha * error * inputs[row][i];    
				}
			}
			epoch++;
		}
		
		System.out.println("Training complete in " + epoch + " epochs.");
		System.out.println(this);
	}
}