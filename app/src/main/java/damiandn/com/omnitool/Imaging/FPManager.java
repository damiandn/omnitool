package damiandn.com.omnitool.Imaging;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desktop on 1/1/2015.
 */
public class FPManager {
//tehse should be moved to hard coded strings in the res section at some point
  //  private static String[] fpArray = {"UV Proteins", "Sirius", "Blue Proteins", "Azurite", "eBFP2", "mKalama1", "mTagBFP2", "TagBFP", "Cyan Proteins", "eCFP", "Cerulean", "mCerulean3", "SCFP3A", "CyPet", "mTurquoise",
     //                                   "mTurquoise2", "TagCFP", "mTFP1", "mMidoriishi-Cyan", "Aquamarine", "Green Proteins", "TurboGFP", "TagGFP2", "mUKG", "Superfolder GFP", "Emerald", "eGFP", "monomeric Azami Green",
     //                                   "mWasabi", "Clover", "mNeonGreen"};
    //private static Integer[] fpEx = {0, 355, 0, 383, 383, 385, 399, 402, 0, 433, 433, 433, 433, 435, 434, 434, 458, 462, 470, 430, 0,  482, 483, 483, 485, 487, 488, 492, 493, 505, 506};
    //private static Integer[] fpEm = {0, 424, 0, 450, 448, 456, 454, 457, 0, 475, 475, 475, 474, 477, 474, 474, 480, 492, 496, 474, 0,  502, 506, 499, 510, 509, 507, 505, 509, 515, 517};
    public static Integer[] backColor = {Color.parseColor("#673AB7"), 0, Color.parseColor("#3F51B5"), 0, 0, 0, 0, 0, Color.parseColor("#00BCD4"), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Color.parseColor("#4CAF50"), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Color.parseColor("#FBC02D"), 0, 0, 0, 0, 0, 0, 0, 0, 0, Color.parseColor("#FF9800"), 0, 0, 0, 0, 0, Color.parseColor("#FF5252"), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Color.parseColor("#D32F2F"), 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static String[] fpArray = {"UV Proteins", "Sirius",	"Blue Proteins", "Azurite", "EBFP2", "mKalama1", "mTagBFP2", "TagBFP", "Cyan Proteins", "ECFP", "Cerulean", "mCerulean3", "SCFP3A", "CyPet", "mTurquoise", "mTurquoise2", "TagCFP", "mTFP1", "mMidoriishi-Cyan", "Aquamarine",
            "Green Proteins", "TurboGFP", "TagGFP2", "mUKG", "Superfolder GFP", "Emerald", "EGFP", "mAzami Green", "mWasabi",	"Clover", "mNeonGreen", "Yellow Proteins", "TagYFP", "EYFP", "Topaz", "Venus",	"SYFP2", "Citrine",	"Ypet",	"lanRFP-ΔS83", "mPapaya1", "Orange Proteins", "mKusabira-Orange",
            "mOrange",	"mOrange2",	"mKOκ",	"mKO2",	"Red Proteins", "TagRFP", "TagRFP-T", "mRuby", "mRuby2", "mTangerine", "mApple", "mStrawberry", "FusionRed", "mCherry",	"mNectarine", "Far-Red Proteins", "mKate2", "HcRed-Tandem", "mPlum", "mRaspberry", "mNeptune", "NirFP",
            "TagRFP657", "TagRFP675", "mCardinal"};

    private static Integer[] fpEx = {0, 355, 0, 383,383,385,399,402, 0, 433,433,433,433,435,434,434,458,462,470,430, 0, 482,483,483,485,487,488,492,493,505,506, 0, 508,513,514,515,515,516,517,521,530, 0, 548,548,549,551,551, 0, 555,555,558,559,568,568,574,580,587,558, 0, 588, 590,590,598,600,605,611,598,604};
    private static Integer[] fpEm = {0, 424, 0, 450,448,456,454,457, 0, 475,475,475,474,477,474,474,480,492,496,474, 0, 502,506,499,510,509,507,505,509,515,517, 0, 524,527,527,528,527,529,530,592,541, 0, 559,562,565,563,565, 0, 584,584,605,600,585,592,596,608,610,578, 0, 633, 637,649,625,650,670,657,675,659};




    private static FPManager mInstance;
    private List<FluorescentProtein> fProteins;             //the list of FPs that will be returned


public static FPManager getInstance() {                     //This is used to we can directly make an instance of this class, I guess, rather than making an object etc

    if (mInstance == null) {
        mInstance = new FPManager();
            }
    return mInstance;
}


    public List<FluorescentProtein> getFPs() {

        if (fProteins == null) {

            fProteins = new ArrayList<FluorescentProtein>();

            for (int i = 0; i < fpArray.length; i++) {                          //iterate through the name array and populate a list of FPs with the matching values from the other arrays

                FluorescentProtein fProtein = new FluorescentProtein();
                fProtein.name = fpArray[i];
                fProtein.Ex = fpEx[i];
                fProtein.Em = fpEm[i];

                Log.v("populate", Integer.toString(i));

                fProtein.backColor = backColor[i];

                fProteins.add(fProtein);
                Log.v("added", fProtein.name);





            }


        }

        return fProteins;

    }








    }







