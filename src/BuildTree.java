import java.math.BigInteger;
import java.util.*;

public class BuildTree {
    //Fetch data regarding the row numbers
    //Calculate gain
    //Split data and generating row numbers for next level
    private Double[][] datasets;
    private TreeNode node;
    private Double[][] subDataset;
    private String[] attributes;
    private HashSet<Integer> AIndices=new HashSet<>();
    private double meanValue=0;
    private double bestGain=-1;
    private double totalPos=0;
    private double totalNeg=0;
    private double rApproved=0;
    private double rDennied=0;
    private double lApproved=0;
    private double lDennied=0;
    private int bestAttrinute=0;
    private ArrayList<Integer> rowIndices;
    private ArrayList<Integer> leftTreerowIndices=new ArrayList<>();
    private ArrayList<Integer> rightreerowIndices=new ArrayList<>();
    public BuildTree(Double[][] datasets , ArrayList<Integer> rowIndices, String[] attributes, TreeNode node){
        this.datasets=datasets;
        this.node=node;
        this.attributes=attributes;
        this.rowIndices=rowIndices;
        ////this.AIndices=aIndices;
        subDataset=new Double[rowIndices.size()][datasets[0].length];
        fetchData(rowIndices);
        for(int i=0;i<attributes.length-1;i++){
            //if (this.AIndices.contains(i)) {
                calculateGain(i);
            //}
        }
        System.out.println("Attribute: "+attributes[bestAttrinute]+" condition: "+meanValue);
        split();
        node.setAttribute(attributes[bestAttrinute]);
        node.setCondition(meanValue);
//        node.setDennied(totalNeg);
//        node.setApproved(totalPos);
        //this.AIndices.remove(bestAttrinute);
        if(checkSplit(leftTreerowIndices)){
            TreeNode leftChild=new TreeNode();
            node.setLeftNode(leftChild);
            leftChild.setApproved(lApproved);
            leftChild.setDennied(lDennied);
            new BuildTree(datasets,leftTreerowIndices,attributes, leftChild);

        }else{
            TreeNode leftChild=new TreeNode();
            node.setLeftNode(leftChild);
            if(lApproved>0){
            leftChild.setAttribute("False clasee");
            }else{
                leftChild.setAttribute("True clasee");
            }
        }
        if(checkSplit(rightreerowIndices)){
            TreeNode rightChild=new TreeNode();
            node.setRightNode(rightChild);
            rightChild.setApproved(rApproved);
           rightChild.setDennied(rDennied);
            new BuildTree(datasets,rightreerowIndices,attributes,rightChild);
        }else{
            TreeNode rightChild=new TreeNode();
            node.setLeftNode(rightChild);
            if(rApproved>0){
                rightChild.setAttribute("False clasee");
            }else{
                rightChild.setAttribute("True clasee");
            }
        }

    }

    private boolean checkSplit(ArrayList<Integer> index){
        boolean flag=false;
        double current=-1;
        for(int i:index){
            if(current==-1){
                current=datasets[i][attributes.length-1];
            }else{
                if(current!=datasets[i][attributes.length-1]){
                    flag=true;
                    break;
                }
            }

        }
        return flag;
    }

    private void split(){
        for(int i:rowIndices){
            if(datasets[i][bestAttrinute]<meanValue){
                leftTreerowIndices.add(i);
            }else{
                rightreerowIndices.add(i);
            }
        }
    }
    private void fetchData(ArrayList<Integer> rowIndices){
        int i=0;
        for(int index:rowIndices){
            subDataset[i]=datasets[index];

            if(subDataset[i][attributes.length-1]==1){
                totalPos++;
            }else{
                totalNeg++;
            }
            i++;
        }
    }
    private void sortData(int aIndex){//sort array

        Arrays.sort(subDataset, new Comparator<Double[]>() {
            @Override
            public int compare(final Double[] entry1, final Double[] entry2) {
                final Double val1 = entry1[aIndex];
                final Double val2 = entry2[aIndex];
                return val1.compareTo(val2);
            }
        });
    }
    private void calculateGain(int aIndex){
        sortData(aIndex);
        int leftPos=0;
        int leftNeg=0;
        double previousLabel=-1;

        for(int i=0; i<subDataset.length;i++){
            double label=subDataset[i][attributes.length-1];
            if(previousLabel==-1){
                previousLabel=label;
            }else{

                if(previousLabel!=label){
                    double mValue=(subDataset[i-1][aIndex]+subDataset[i][aIndex])/2;
                    double gain=calculateEntropy(leftPos,leftNeg,totalPos-leftPos,totalNeg-leftNeg);
                    if(gain>bestGain){
                        //System.out.println("Best Gain: "+bestGain+" Current gain: "+gain);

                        bestGain=gain;
                        meanValue=mValue;
                        bestAttrinute=aIndex;
                        lApproved=leftPos;
                        lDennied=leftNeg;
                        rApproved=totalPos-leftPos;
                        rDennied=totalNeg-leftNeg;
//                        System.out.println("Best attribute: "+attributes[bestAttrinute]);
//                        System.out.println("Left Approved: "+lApproved+" Left Dennied: "+lDennied);
//                        System.out.println("Right Approved: "+rApproved+" RIght Dennied: "+rDennied);

                    }
                }
                if(label==1){
                    leftPos++;
                }else{
                    leftNeg++;
                }
                previousLabel=label;
            }

        }

    }
    private double calculateEntropy(double leftPos, double leftNeg, double rightPos, double rightNeg) {
        double leftTotal = leftNeg + leftPos;
        double rightTotal = rightNeg + rightPos;
        double total = leftTotal + rightTotal;

        /** Entropy for the whole split */
        double generalEntropy = -((leftPos + rightPos) / total) * log2((leftPos + rightPos) / total)
                - ((leftNeg + rightNeg) / total) * log2((leftNeg + rightNeg) / total);

        /** Entropy for left(positive branch) */
        double result=0;
//        double result = -(leftTotal / total)
//                * ((leftPos / leftTotal) * log2(leftPos / leftTotal)
//                + (leftNeg / leftTotal) * log2(leftNeg / leftTotal))
//                /** Entropy for right(negative) branch */
//                - (rightTotal / total) * ((rightPos / rightTotal) * log2(rightPos / rightTotal)
//                + (rightNeg / rightTotal) * log2(rightNeg / rightTotal));

                if(leftTotal==0){
                    result=- (rightTotal / total) * ((rightPos / rightTotal) * log2(rightPos / rightTotal)
                            + (rightNeg / rightTotal) * log2(rightNeg / rightTotal));
                }else if(rightTotal==0){
                    result=-(leftTotal / total)
                            * ((leftPos / leftTotal) * log2(leftPos / leftTotal)
                            + (leftNeg / leftTotal) * log2(leftNeg / leftTotal));
                }else{
                    result=-(leftTotal / total)
                            * ((leftPos / leftTotal) * log2(leftPos / leftTotal)
                            + (leftNeg / leftTotal) * log2(leftNeg / leftTotal))
                            /** Entropy for right(negative) branch */
                            - (rightTotal / total) * ((rightPos / rightTotal) * log2(rightPos / rightTotal)
                            + (rightNeg / rightTotal) * log2(rightNeg / rightTotal));
                }

//        if (Double.isNaN(result)){
//            System.out.println("Nan");
//            return 0.1;
//        } // if attribute can split the data
//            System.out.println("Gain=" + generalEntropy + " - " + result + " = " + (generalEntropy - result));
        return generalEntropy - result;
    }
    private double log2(double number) {
        if (number == 0)
            return 0;
        return Math.log((number)) / Math.log(2);
    }

}
