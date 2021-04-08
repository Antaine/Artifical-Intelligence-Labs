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

/*
 * 	------------------------------------------------------------------------
 * 	B.Sc. (Hons) in Software Development - Artificial Intelligence
 * 	------------------------------------------------------------------------
 *  
 *  Data set for the Neural Network Zoo Animal Classifier Lab
 * 
 * 
 * 1. Title: Zoo database
 * 
 * 2. Source Information
 *    -- Creator: Richard Forsyth
 *    -- Donor: Richard S. Forsyth 
 *              8 Grosvenor Avenue
 *              Mapperley Park
 *              Nottingham NG3 5DX
 *              0602-621676
 *   -- Date: 5/15/1990
 *  
 * 3. Past Usage:
 *    -- None known other than what is shown in Forsyth's PC/BEAGLE User's Guide.
 * 
 * 4. Relevant Information:
 *   -- A simple database containing 17 Boolean-valued attributes.  The "type"
 *      attribute appears to be the class attribute.  Here is a breakdown of
 *       which animals are in which type: (I find it unusual that there are
 *       2 instances of "frog" and one of "girl"!)
 * 
 *       Class# Set of animals:
 *       ====== ===============================================================
 *            1 (41) aardvark, antelope, bear, boar, buffalo, calf,
 *                   cavy, cheetah, deer, dolphin, elephant,
 *                   fruitbat, giraffe, girl, goat, gorilla, hamster,
 *                   hare, leopard, lion, lynx, mink, mole, mongoose,
 *                   opossum, oryx, platypus, polecat, pony,
 *                   porpoise, puma, pussycat, raccoon, reindeer,
 *                   seal, sealion, squirrel, vampire, vole, wallaby,wolf
 *            2 (20) chicken, crow, dove, duck, flamingo, gull, hawk,
 *                   kiwi, lark, ostrich, parakeet, penguin, pheasant,
 *                   rhea, skimmer, skua, sparrow, swan, vulture, wren
 *            3 (5)  pitviper, seasnake, slowworm, tortoise, tuatara 
 *            4 (13) bass, carp, catfish, chub, dogfish, haddock,
 *                   herring, pike, piranha, seahorse, sole, stingray, tuna
 *            5 (4)  frog, frog, newt, toad 
 *            6 (8)  flea, gnat, honeybee, housefly, ladybird, moth, termite, wasp
 *            7 (10) clam, crab, crayfish, lobster, octopus,
 *                  scorpion, seawasp, slug, starfish, worm
 * 
 * 5. Number of Instances: 101
 * 
 * 6. Number of Attributes: 18 (animal name, 15 Boolean attributes, 2 numerics)
 * 
 * 7. Attribute Information: (name of attribute and type of value domain)
 *    1. animal name:  Unique for each instance
 *    2. hair			Boolean
 *    3. feathers		Boolean
 *    4. eggs			Boolean
 *    5. milk			Boolean
 *    6. airborne		Boolean
 *    7. aquatic		Boolean
 *    8. predator		Boolean
 *    9. toothed		Boolean
 *   10. backbone		Boolean
 *   11. breathes		Boolean
 *   12. venomous		Boolean
 *   13. fins			Boolean
 *   14. legs			Numeric (set of values: {0,2,4,5,6,8})
 *   15. tail			Boolean
 *   16. domestic		Boolean
 *   17. catsize		Boolean
 *   18. type			Numeric (integer values in range [1,7])
 * 
 * 8. Missing Attribute Values: None
 * 
 * 9. Class Distribution: Given above
 * 
 */

public class ZooRunner {
	private double[][] data = { { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 1, 1 },
			{ 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0 }, { 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 0, 1, 0 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 1, 0 }, { 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0.5, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0.75, 0, 0, 0 },
			{ 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 }, { 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1 },
			{ 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 1, 0 },
			{ 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 }, { 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 0, 1 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.75, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0.5, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0.5, 0, 0, 0 }, { 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0.25, 1, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.25, 0, 1, 1 },
			{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0.75, 0, 0, 0 }, { 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 1, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.25, 0, 0, 1 },
			{ 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 1, 0 }, { 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 }, { 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
			{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0.75, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0.75, 0, 0, 0 },
			{ 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 },
			{ 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0.75, 0, 0, 0 },
			{ 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0.75, 0, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 0 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0.75, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 0, 1 },
			{ 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 1, 0 },
			{ 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 1 },
			{ 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 }, { 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1 },
			{ 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0 },
			{ 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 1, 1 }, { 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 1, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 1, 1 },
			{ 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 1 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 }, { 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0.25, 1, 0, 1 }, { 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.25, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0.625, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
			{ 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0.25, 1, 0, 1 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.75, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0.5, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0.5, 1, 0, 1 }, { 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1 }, { 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0.25, 1, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.5, 1, 0, 0 }, { 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0.25, 1, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0.25, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0.75, 0, 0, 0 }, { 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0.5, 1, 0, 1 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0.25, 1, 0, 0 } };

	private double[][] expected = { { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 1, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0, 0 } };	
	
	
	public void go() {
		//----------------------------------------------------
		//Step 1: Declare Network Topology
		//----------------------------------------------------
		System.out.println("[INFO] Creating neural network");
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null, true, 16));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 2));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 7));
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
		
		double minError = 0.08; //Change and see the effect on the result... :)
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
		new ZooRunner().go();
	}	
}
