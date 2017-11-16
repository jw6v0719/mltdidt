import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
    private Double[][] dataSet;
    private String[] attributes;
    private Double[][] trainData;
    private Double[][] validateData;
    private int validStart=0;


    public DataReader(String fileName){
            readFile(fileName);
            fetchTrainData();
            fetchValidData();
    }
    private void readFile(String fileName){
        ArrayList<Double[]> tmpRowCollection=new ArrayList<>();

        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            int i=-1;
            while (br.ready()) {
                String line=br.readLine();
                if(i==-1){
                    attributes=line.split(",");

                }else{
                    String tmp[]=line.split(",");
                    ArrayList<Double> tmpList=new ArrayList<>();
                    for(String value: tmp){
                        tmpList.add(Double.parseDouble(value));
                    }
                    tmpRowCollection.add(tmpList.toArray(new Double[0]));
                }
                i++;
            }
            fr.close();
            dataSet=new Double[tmpRowCollection.size()][attributes.length];
            i=0;
            for(Double[] row:tmpRowCollection){

                dataSet[i]=row;
                i++;
            }

        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
    private void fetchTrainData(){
        int row=(int)Math.ceil(dataSet.length*0.7);
        validStart=row+1;
        trainData=new Double[row][attributes.length];
        for(int i=0;i<row;i++){
            trainData[i]=dataSet[i];
        }
    }
    private void fetchValidData(){
        validateData=new Double[dataSet.length-validStart][attributes.length];
        for(int i =0;i<validateData.length;i++){
            validateData[i]=dataSet[validStart+i];
        }
    }

    public Double[][] getDataSet(){
        return this.dataSet;
    }
    public String[] getAttributes(){
        return this.attributes;
    }
    public Double[][] getTrainData(){return this.trainData;}
    public Double[][] getValidateData(){return this.validateData;}
}
