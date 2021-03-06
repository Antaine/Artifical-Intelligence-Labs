package ie.gmit.sw.ai;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

public class XORRunner {

	private double[][] data = { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 } };
	private double[][] expected = { { 0.0 }, { 1.0 }, { 1.0 }, { 0.0 } };
	
	public void go() {
		//----------------------------------------------------
		//Step 1: Declare Network Topology
		//----------------------------------------------------
		System.out.println("[INFO] Creating neural network");
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null, true, 2));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 5));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
		network.getStructure().finalizeStructure();
		network.reset();
		
		
		//----------------------------------------------------
		//Step 2: Create the training data set
		//----------------------------------------------------
		System.out.println("[INFO] Creating training set");
		MLDataSet trainingSet = new BasicMLDataSet(data, expected);

		
		//----------------------------------------------------
		//Step 3: Train the NN
		//----------------------------------------------------
		System.out.println("[INFO] Training the network...");
		ResilientPropagation train = new ResilientPropagation(network, trainingSet);
		
		double minError = 0.09; //Change and see the effect on the result... :)
		int epoch = 1;
		do {
			train.iteration();
			//System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;
		} while (train.getError() > minError);
		train.finishTraining();
		System.out.println("[INFO] training complete in " + epoch + " epochs with error=" + train.getError());
		

		//----------------------------------------------------
		//Step 4: Test the NN
		//----------------------------------------------------		
		System.out.println("[INFO] Testing the network:");
		for(MLDataPair pair: trainingSet ) {
			MLData output = network.compute(pair.getInput());
			System.out.println(pair.getInput().getData(0) + "," 
							 + pair.getInput().getData(1)
							 + ", Y=" + (int)Math.round(output.getData(0)) 
							 + ", Yd=" + (int) pair.getIdeal().getData(0));
		}
	
		//----------------------------------------------------
		//Step 5: Shutdown the NN
		//----------------------------------------------------	
		System.out.println("[INFO] Shutting down.");
		Encog.getInstance().shutdown();
	}
	
	public static void main(String[] args) {
		new XORRunner().go();
	}	
}
