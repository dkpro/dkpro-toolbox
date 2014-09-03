
/* First created by JCasGen Wed Sep 03 16:40:55 CEST 2014 */
package de.tudarmstadt.ukp.dkpro.wsd.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Sep 03 16:40:55 CEST 2014
 * @generated */
public class WSDItem_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (WSDItem_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = WSDItem_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new WSDItem(addr, WSDItem_Type.this);
  			   WSDItem_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new WSDItem(addr, WSDItem_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = WSDItem.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
 
  /** @generated */
  final Feature casFeat_subjectOfDisambiguation;
  /** @generated */
  final int     casFeatCode_subjectOfDisambiguation;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSubjectOfDisambiguation(int addr) {
        if (featOkTst && casFeat_subjectOfDisambiguation == null)
      jcas.throwFeatMissing("subjectOfDisambiguation", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    return ll_cas.ll_getStringValue(addr, casFeatCode_subjectOfDisambiguation);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSubjectOfDisambiguation(int addr, String v) {
        if (featOkTst && casFeat_subjectOfDisambiguation == null)
      jcas.throwFeatMissing("subjectOfDisambiguation", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    ll_cas.ll_setStringValue(addr, casFeatCode_subjectOfDisambiguation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_constituents;
  /** @generated */
  final int     casFeatCode_constituents;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getConstituents(int addr) {
        if (featOkTst && casFeat_constituents == null)
      jcas.throwFeatMissing("constituents", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    return ll_cas.ll_getRefValue(addr, casFeatCode_constituents);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setConstituents(int addr, int v) {
        if (featOkTst && casFeat_constituents == null)
      jcas.throwFeatMissing("constituents", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    ll_cas.ll_setRefValue(addr, casFeatCode_constituents, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getConstituents(int addr, int i) {
        if (featOkTst && casFeat_constituents == null)
      jcas.throwFeatMissing("constituents", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_constituents), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_constituents), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_constituents), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setConstituents(int addr, int i, int v) {
        if (featOkTst && casFeat_constituents == null)
      jcas.throwFeatMissing("constituents", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_constituents), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_constituents), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_constituents), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_pos;
  /** @generated */
  final int     casFeatCode_pos;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPos(int addr) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pos);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPos(int addr, String v) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "de.tudarmstadt.ukp.dkpro.wsd.type.WSDItem");
    ll_cas.ll_setStringValue(addr, casFeatCode_pos, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public WSDItem_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_subjectOfDisambiguation = jcas.getRequiredFeatureDE(casType, "subjectOfDisambiguation", "uima.cas.String", featOkTst);
    casFeatCode_subjectOfDisambiguation  = (null == casFeat_subjectOfDisambiguation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subjectOfDisambiguation).getCode();

 
    casFeat_constituents = jcas.getRequiredFeatureDE(casType, "constituents", "uima.cas.FSArray", featOkTst);
    casFeatCode_constituents  = (null == casFeat_constituents) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_constituents).getCode();

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_pos = jcas.getRequiredFeatureDE(casType, "pos", "uima.cas.String", featOkTst);
    casFeatCode_pos  = (null == casFeat_pos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pos).getCode();

  }
}



    