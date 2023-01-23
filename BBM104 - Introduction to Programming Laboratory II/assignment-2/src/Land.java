public class Land extends Property{

    public Land(String[] arr) {

        super(arr);

        // Set rent:
        if (getCOST() >= 0 & getCOST() <= 2000)
            setRent((getCOST()*4)/10);
        else if (getCOST()>= 2001 & getCOST() <= 3000)
            setRent((getCOST()*3)/10);
        else if (getCOST()>= 3001 & getCOST() <= 4000)
            setRent((getCOST()*35)/100);
        // ---

    }
}
