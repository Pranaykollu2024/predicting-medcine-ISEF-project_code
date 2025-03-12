import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;



public class PersonalizedMedicine {
    public static void main(String[] args) throws Exception {
        // Load the data
        String csvFile = "genetic_data.csv";
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        Instances data = new Instances(reader);
        reader.close();

        // Set class attribute (assuming it's the last attribute)
        data.setClassIndex(data.numAttributes() - 1);

        // Split the data into training and testing sets
        Instances[] split = splitDataset(data, 0.8);
        Instances trainingData = split[0]; 
        Instances testingData = split[1];

        // Train the model
        RandomForest model = new RandomForest();
        model.buildClassifier(trainingData);

        // Make predictions and evaluate the model
        evaluateModel(model, testingData);
    }

    private static Instances[] splitDataset(Instances data, double trainRatio) {
        int trainSize = (int) Math.round(data.numInstances() * trainRatio);
        int testSize = data.numInstances() - trainSize;

        Instances train = new Instances(data, 0, trainSize);
        Instances test = new Instances(data, trainSize, testSize);

        return new Instances[]{train, test};
    }

    private static void evaluateModel(Classifier model, Instances testingData) throws Exception {
        int correct = 0;

        for (int i = 0; i < testingData.numInstances(); i++) {
            Instance instance = testingData.instance(i);
            double predictedClass = model.classifyInstance(instance);
            if (predictedClass == instance.classValue()) {
                correct++;
            }
        }

        double accuracy = (double) correct / testingData.numInstances();
        System.out.printf("Accuracy: %.2f%n", accuracy);
    }
}

