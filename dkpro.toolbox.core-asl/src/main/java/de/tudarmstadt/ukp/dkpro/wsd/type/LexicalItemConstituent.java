

/* First created by JCasGen Wed Sep 03 16:40:55 CEST 2014 */
package de.tudarmstadt.ukp.dkpro.wsd.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 03 16:40:55 CEST 2014
 * XML source: /Users/zesch/Documents/workspace/dkpro.toolbox/dkpro.toolbox.core-asl/src/main/resources/desc/type/WSDItem.xml
 * @generated */
public class LexicalItemConstituent extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(LexicalItemConstituent.class);
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
  protected LexicalItemConstituent() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public LexicalItemConstituent(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public LexicalItemConstituent(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public LexicalItemConstituent(JCas jcas, int begin, int end) {
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
  //* Feature: constituentType

  /** getter for constituentType - gets Type of the constituent, e.g. head or particle.
   * @generated
   * @return value of the feature 
   */
  public String getConstituentType() {
    if (LexicalItemConstituent_Type.featOkTst && ((LexicalItemConstituent_Type)jcasType).casFeat_constituentType == null)
      jcasType.jcas.throwFeatMissing("constituentType", "de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LexicalItemConstituent_Type)jcasType).casFeatCode_constituentType);}
    
  /** setter for constituentType - sets Type of the constituent, e.g. head or particle. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setConstituentType(String v) {
    if (LexicalItemConstituent_Type.featOkTst && ((LexicalItemConstituent_Type)jcasType).casFeat_constituentType == null)
      jcasType.jcas.throwFeatMissing("constituentType", "de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
    jcasType.ll_cas.ll_setStringValue(addr, ((LexicalItemConstituent_Type)jcasType).casFeatCode_constituentType, v);}    
   
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (LexicalItemConstituent_Type.featOkTst && ((LexicalItemConstituent_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LexicalItemConstituent_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (LexicalItemConstituent_Type.featOkTst && ((LexicalItemConstituent_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
    jcasType.ll_cas.ll_setStringValue(addr, ((LexicalItemConstituent_Type)jcasType).casFeatCode_id, v);}    
  }

    