package de.cau.cs.kieler.klighd.structuredEditMsg;

/**
 * This class holds the keyinformation what inputs need to be done on the client for a specific
 * action. The label is displayed to the user in the contextmenu. The kind is the action kind.
 * Mergable is when multiple things are selected and it can be done for all of them The InputType
 * array depicts a ordered way of asking the user for inputs.
 * 
 * @author felixj
 */
public class StructuredEditMsg {

    String label;
    String kind;
    Boolean mergable;
    InputType[] inputs;

    public StructuredEditMsg(String label, String kind, Boolean mergable, InputType[] inputs) {
        this.label = label;
        this.kind = kind;
        this.mergable = mergable;
        this.inputs = inputs;
    }
}
