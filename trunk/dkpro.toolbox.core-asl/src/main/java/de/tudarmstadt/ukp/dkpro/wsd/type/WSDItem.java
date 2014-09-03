

/* First created by JCasGen Wed Sep 03 16:40:55 CEST 2014 */
package de.tudarmstadt.ukp.dkpro.wsd.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 03 16:40:55 CEST 2014
 * XML source: /Users/zesch/Documents/workspace/dkpro.toolbox/dkpro.toolbox.core-asl/src/main/resources/desc/type/WSDItem.xml
 * @generated */
public class WSDItem extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(WSDItem.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected WSDItem() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public WSDItem(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public WSDItem(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public WSDItem(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: subjectOfDisambiguation

  /** getter for subjectOfDisambiguation - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSubjectOfDisambiguation() {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_subjectOfDisambiguation == null)
      jcasType.jcas.throwFeatMissing("subjectOfDisambiguation", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WSDItem_Type)jcasType).casFeatCode_subjectOfDisambiguation);}
    
  /** setter for subjectOfDisambiguation - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSubjectOfDisambiguation(String v) {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_subjectOfDisambiguation == null)
      jcasType.jcas.throwFeatMissing("subjectOfDisambiguation", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    jcasType.ll_cas.ll_setStringValue(addr, ((WSDItem_Type)jcasType).casFeatCode_subjectOfDisambiguation, v);}    
   
    
  //*--------------*
  //* Feature: constituents

  /** getter for constituents - gets Array of all constituents for this WSDItem.
   * @generated
   * @return value of the feature 
   */
  public FSArray getConstituents() {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_constituents == null)
      jcasType.jcas.throwFeatMissing("constituents", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((WSDItem_Type)jcasType).casFeatCode_constituents)));}
    
  /** setter for constituents - sets Array of all constituents for this WSDItem. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setConstituents(FSArray v) {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_constituents == null)
      jcasType.jcas.throwFeatMissing("constituents", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    jcasType.ll_cas.ll_setRefValue(addr, ((WSDItem_Type)jcasType).casFeatCode_constituents, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for constituents - gets an indexed value - Array of all constituents for this WSDItem.
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public LexicalItemConstituent getConstituents(int i) {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_constituents == null)
      jcasType.jcas.throwFeatMissing("constituents", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((WSDItem_Type)jcasType).casFeatCode_constituents), i);
    return (LexicalItemConstituent)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((WSDItem_Type)jcasType).casFeatCode_constituents), i)));}

  /** indexed setter for constituents - sets an indexed value - Array of all constituents for this WSDItem.
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setConstituents(int i, LexicalItemConstituent v) { 
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_constituents == null)
      jcasType.jcas.throwFeatMissing("constituents", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((WSDItem_Type)jcasType).casFeatCode_constituents), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((WSDItem_Type)jcasType).casFeatCode_constituents), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WSDItem_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    jcasType.ll_cas.ll_setStringValue(addr, ((WSDItem_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: pos

  /** getter for pos - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPos() {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WSDItem_Type)jcasType).casFeatCode_pos);}
    
  /** setter for pos - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPos(String v) {
    if (WSDItem_Type.featOkTst && ((WSDItem_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    jcasType.ll_cas.ll_setStringValue(addr, ((WSDItem_Type)jcasType).casFeatCode_pos, v);}    
  }

    