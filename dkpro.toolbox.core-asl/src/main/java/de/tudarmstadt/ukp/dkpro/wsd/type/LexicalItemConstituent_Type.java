
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
public class LexicalItemConstituent_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (LexicalItemConstituent_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = LexicalItemConstituent_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new LexicalItemConstituent(addr, LexicalItemConstituent_Type.this);
  			   LexicalItemConstituent_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new LexicalItemConstituent(addr, LexicalItemConstituent_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = LexicalItemConstituent.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
 
  /** @generated */
  final Feature casFeat_constituentType;
  /** @generated */
  final int     casFeatCode_constituentType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getConstituentType(int addr) {
        if (featOkTst && casFeat_constituentType == null)
      jcas.throwFeatMissing("constituentType", "de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
    return ll_cas.ll_getStringValue(addr, casFeatCode_constituentType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setConstituentType(int addr, String v) {
        if (featOkTst && casFeat_constituentType == null)
      jcas.throwFeatMissing("constituentType", "de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
    ll_cas.ll_setStringValue(addr, casFeatCode_constituentType, v);}
    
  
 
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
      jcas.throwFeatMissing("id", "de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "de.tudarmstadt.ukp.dkpro.wsd.type.LexicalItemConstituent");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public LexicalItemConstituent_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_constituentType = jcas.getRequiredFeatureDE(casType, "constituentType", "uima.cas.String", featOkTst);
    casFeatCode_constituentType  = (null == casFeat_constituentType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_constituentType).getCode();

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

  }
}



    