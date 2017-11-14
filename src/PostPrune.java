public class PostPrune {

    private TreeNode root;
    public PostPrune(TreeNode root){
        this.root=root;
        process();
    }
    private void process(){
        prune(root);
    }

    private void prune(TreeNode node){
        //TreeNode sibling=node.getSibling();
        if((node.getAttribute().equals("False")||node.getAttribute().contains("True")))
                //&& (sibling.getAttribute().equals("False")||sibling.getAttribute().contains("True")))
        {
            if(checkAccuracy(node)){
                eliminate(node.getParentNode());
            }else{
                if(node.getParentNode()!=null){
                prune(node.getParentNode());
                }
            }

        }else{
            if(node.getLeftNode()!=null){
                prune(node.getLeftNode());
            }else if(node.getRightNode()!=null){
                prune(node.getRightNode());
            }
        }
    }

    private boolean checkAccuracy(TreeNode node){
        double parentPos=node.getParentNode().getApproved();
        double parentNeg=node.getParentNode().getDennied();
        double pos = node.getApproved();
        double neg=node.getDennied();
        double sibPos=node.getSibling().getApproved();
        double sibNeg=node.getSibling().getDennied();

        double correct=0;
        double siblingCorrect=0;
        double parCorrect=0;

        boolean flag=false;
        if(node.getClassLabel().equals("True")){
            correct=pos;
        }else{
            correct=neg;
        }
        if(node.getSibling().getClassLabel().equals("True")){
            siblingCorrect=sibPos;
        }else{
            siblingCorrect=sibNeg;
        }
        double curAccuracy=(correct+siblingCorrect)/(pos+neg+sibPos+sibNeg);
        if(node.getParentNode().getClassLabel().equals("True")){
            parCorrect=parentPos;
        }else{
            parCorrect=parentNeg;
        }
        double parentAccracy=parCorrect/(parentNeg+parentPos);
        if(parentAccracy>curAccuracy){
            flag=true;
        }

        return flag;
    }

    private void eliminate(TreeNode node){
        node.setRightNode(null);
        node.setLeftNode(null);
        prune(node);
    }

}
