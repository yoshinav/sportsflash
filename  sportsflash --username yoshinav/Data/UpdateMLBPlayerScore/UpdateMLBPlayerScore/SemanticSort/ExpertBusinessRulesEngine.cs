using System;
using System.Data;
using System.Configuration;
using System.Collections;
using WordsMatching;
using CS_767___Chaining;

/// Author:  Navdeep Alam
/// CS 767 Summer 2007
/// Date: July 20, 2007
/// Prof: Dr. Eric Braude
/// <summary>
/// ExpertBusinessRulesEngine:  Expert AI System for handling rules for FAQ and GA Search Analysis.
/// </summary>
/// 

public class ExpertBusinessRulesEngine
{
    //Static variable to access WordNet Sentence Similarity Engine
    static SentenceSimilarity semsim = new SentenceSimilarity();
    //Create FactList using ArrayList which can hold any type of data (i.e. strings, int, ... etc)
    ArrayList FactListFCTest = new ArrayList();      //Fact List for Forward Chaining Test
    ArrayList FactListBCTest = new ArrayList();      //Fact List for Forward Chaining Test
    static private double MutateScore = 0.9;         //Mutuate Random Variable
    static private Fact ValidateFact = new Fact();   //Fact to be validated

    //FAQ Category Association Facts
    static Fact CatA = new Fact();
    static Fact CatB = new Fact();
    static Fact CatC = new Fact();
    static Fact MainCat = new Fact();

    //FAQ Web Mashup Association Facts
    static Fact WebMashupA = new Fact();
    static Fact WebMashupB = new Fact();
    static Fact WebMashupC = new Fact();
    static Fact MainWebMashup = new Fact();

	public ExpertBusinessRulesEngine()
	{

    }

    //Properties for various variables
    public Fact CATA
    {
        get
        {
            return CatA;
        }

        set
        {
            CatA = value;
        }
    }

    public Fact CATB
    {
        get
        {
            return CatB;
        }

        set
        {
            CatB = value;
        }
    }

    public Fact CATC
    {
        get
        {
            return CatC;
        }

        set
        {
            CatC = value;
        }
    }

    public Fact WEBMASHUPA
    {
        get
        {
            return WebMashupA;
        }

        set
        {
            WebMashupA = value;
        }
    }

    public Fact WEBMASHUPB
    {
        get
        {
            return WebMashupB;
        }

        set
        {
            WebMashupB = value;
        }
    }

    public Fact WEBMASHUPC
    {
        get
        {
            return WebMashupC;
        }

        set
        {
            WebMashupC = value;
        }
    }

    public double MUTATESCORE
    {
        get
        {
            return MutateScore;
        }

        set
        {
            MutateScore = value;
        }
    }

    public Fact MAINCAT
    {
        get
        {
            return MainCat;
        }

        set
        {
            MainCat = value;
        }
    }

    public Fact MAINWEBMASHUP
    {
        get
        {
            return MainWebMashup;
        }

        set
        {
            MainWebMashup = value;
        }
    }

    public Fact VALIDATEFACT
    {
        get
        {
            return ValidateFact;
        }

        set
        {
            ValidateFact = value;
        }
    }

    //Examine Category Fact Against Business Rules
    public bool ExamineBusinessRulesCategory(Fact validateFact)
    {

        ValidateFact = validateFact;      //Define Fact to be Validated
        //This fact is of type object, which means it can be anything (i.e. strings, int, ... etc)

        FactListFCTest.Add(CatA);                         // CatA is a fact
        FactListFCTest.Add(CatB);                         // CatB is a fact
        FactListFCTest.Add(CatC);                         // CatC is a fact
        FactListBCTest.AddRange(FactListFCTest);

        //Define Rules - Antecedents and Consequents
        ArrayList AntecedentsFCR1 = new ArrayList();
        ArrayList ConsequentsFCR1 = new ArrayList();
        ArrayList AntecedentsFCR2 = new ArrayList();
        ArrayList ConsequentsFCR2 = new ArrayList();
        ArrayList AntecedentsFCR3 = new ArrayList();
        ArrayList ConsequentsFCR3 = new ArrayList();
        ArrayList AntecedentsFCR4 = new ArrayList();
        ArrayList ConsequentsFCR4 = new ArrayList();
        ArrayList AntecedentsBCR1 = new ArrayList();
        ArrayList ConsequentsBCR1 = new ArrayList();
        ArrayList AntecedentsBCR2 = new ArrayList();
        ArrayList ConsequentsBCR2 = new ArrayList();
        ArrayList AntecedentsBCR3 = new ArrayList();
        ArrayList ConsequentsBCR3 = new ArrayList();
        ArrayList AntecedentsBCR4 = new ArrayList();
        ArrayList ConsequentsBCR4 = new ArrayList();
        Hashtable RuleListFCTest = new Hashtable();      //HashTable contains Rule List for Forward Chaining Test
        Hashtable RuleListBCTest = new Hashtable();      //HashTable contains Rule List for Backward Chaining Test

        //Business Rule #1
        AntecedentsFCR1.Add(CatA.SimilarityScore > 0.6);
        AntecedentsFCR1.Add(CatA.IsHighestScore = true);
        ConsequentsFCR1.Add(CatA);
        AntecedentsBCR1.AddRange(AntecedentsFCR1);
        ConsequentsBCR1.AddRange(ConsequentsFCR1);

        //Business Rule #2
        AntecedentsFCR2.Add(CatB.SimilarityScore > 0.6);
        AntecedentsFCR2.Add(CatB.IsHighestScore = true);
        ConsequentsFCR2.Add(CatB);
        AntecedentsBCR2.AddRange(AntecedentsFCR2);
        ConsequentsBCR2.AddRange(ConsequentsFCR2);

        // For Testing Purposes By Pass Weighting Rules
        //Business Rule #3 -- Weighting Advantage for Category B
        //AntecedentsFCR3.Add(CatB.SimilarityScore + 0.2 > 0.8);
        //AntecedentsFCR3.Add(CatA.IsHighestScore = true);
        //ConsequentsFCR3.Add(CatB);
        //AntecedentsBCR3.AddRange(AntecedentsFCR3);
        //ConsequentsBCR3.AddRange(ConsequentsFCR3);

        // For Testing Purposes By Pass Weighting Rules
        //Business Rule #4 -- Weighting Advantage for Category A
        //AntecedentsFCR4.Add(CatA.SimilarityScore + 0.1 > 0.9);
        //AntecedentsFCR4.Add(CatB.IsHighestScore = true);
        //ConsequentsFCR4.Add(CatA);
        //AntecedentsBCR4.AddRange(AntecedentsFCR4);
        //ConsequentsBCR4.AddRange(ConsequentsFCR4);

        //Add Each Business Rule to the RuleList HashTables
        RuleListFCTest.Add(AntecedentsFCR1, ConsequentsFCR1);
        RuleListFCTest.Add(AntecedentsFCR2, ConsequentsFCR2);
        //RuleListFCTest.Add(AntecedentsFCR3, ConsequentsFCR3);
        //RuleListFCTest.Add(AntecedentsFCR4, ConsequentsFCR4);

        RuleListBCTest.Add(AntecedentsBCR1, ConsequentsBCR1);
        RuleListBCTest.Add(AntecedentsBCR2, ConsequentsBCR2);
        //RuleListBCTest.Add(AntecedentsBCR3, ConsequentsBCR3);
        //RuleListBCTest.Add(AntecedentsBCR4, ConsequentsBCR4);

        IDictionaryEnumerator _enumerator = RuleListFCTest.GetEnumerator();

        //Perform Forward Chaining to Validate Rule
        return BRForwardChainingEvaluate(FactListFCTest, RuleListFCTest);

        //Perform Backward Chaining to Validate Rule
        //return BRBackwardChainingExample(FactListBCTest, RuleListBCTest);

    }

    //Examine Web Mashup Fact Against Business Rules
    public bool ExamineBusinessRulesWebMashup(Fact validateFact)
    {
        ValidateFact = validateFact;      //Define Fact to be Validated
        //This fact is of type object, which means it can be anything (i.e. strings, int, ... etc)

        FactListFCTest.Clear();
        FactListBCTest.Clear();

        FactListFCTest.Add(WebMashupA);                         // WebMashupA is a fact
        FactListFCTest.Add(WebMashupB);                         // WebMashupB is a fact
        FactListFCTest.Add(WebMashupC);                         // WebMashupC is a fact
        FactListBCTest.AddRange(FactListFCTest);

        //Define Rules - Antecedents and Consequents
        ArrayList AntecedentsFCR1 = new ArrayList();
        ArrayList ConsequentsFCR1 = new ArrayList();
        ArrayList AntecedentsFCR2 = new ArrayList();
        ArrayList ConsequentsFCR2 = new ArrayList();
        ArrayList AntecedentsFCR3 = new ArrayList();
        ArrayList ConsequentsFCR3 = new ArrayList();
        ArrayList AntecedentsFCR4 = new ArrayList();
        ArrayList ConsequentsFCR4 = new ArrayList();
        ArrayList AntecedentsBCR1 = new ArrayList();
        ArrayList ConsequentsBCR1 = new ArrayList();
        ArrayList AntecedentsBCR2 = new ArrayList();
        ArrayList ConsequentsBCR2 = new ArrayList();
        ArrayList AntecedentsBCR3 = new ArrayList();
        ArrayList ConsequentsBCR3 = new ArrayList();
        ArrayList AntecedentsBCR4 = new ArrayList();
        ArrayList ConsequentsBCR4 = new ArrayList();
        Hashtable RuleListFCTest = new Hashtable();      //HashTable contains Rule List for Forward Chaining Test
        Hashtable RuleListBCTest = new Hashtable();      //HashTable contains Rule List for Backward Chaining Test

        //Business Rule #1
        AntecedentsFCR1.Add(WebMashupA.SimilarityScore > 0.4);
        AntecedentsFCR1.Add(WebMashupA.IsHighestScore = true);
        ConsequentsFCR1.Add(WebMashupA);
        AntecedentsBCR1.AddRange(AntecedentsFCR1);
        ConsequentsBCR1.AddRange(ConsequentsFCR1);

        //Business Rule #2
        AntecedentsFCR2.Add(WebMashupB.SimilarityScore > 0.4);
        AntecedentsFCR2.Add(WebMashupB.IsHighestScore = true);
        ConsequentsFCR2.Add(WebMashupB);
        AntecedentsBCR2.AddRange(AntecedentsFCR2);
        ConsequentsBCR2.AddRange(ConsequentsFCR2);

        // For Testing Purposes By Pass Weighting Rules
        //Business Rule #3 -- Weighting Advantage for Web Mashup B
        //AntecedentsFCR3.Add(WebMashupB.SimilarityScore + 0.2 > 0.7);
        //AntecedentsFCR3.Add(WebMashupA.IsHighestScore = true);
        //ConsequentsFCR3.Add(WebMashupB);
        //AntecedentsBCR3.AddRange(AntecedentsFCR3);
        //ConsequentsBCR3.AddRange(ConsequentsFCR3);

        // For Testing Purposes By Pass Weighting Rules
        //Business Rule #4 -- Weighting Advantage for Web Mashup B
        //AntecedentsFCR4.Add(WebMashupA.SimilarityScore + 0.1 > 0.8);
        //AntecedentsFCR4.Add(WebMashupB.IsHighestScore = true);
        //ConsequentsFCR4.Add(WebMashupA);
        //AntecedentsBCR4.AddRange(AntecedentsFCR4);
        //ConsequentsBCR4.AddRange(ConsequentsFCR4);

        //Add Each Business Rule to the RuleList HashTables
        RuleListFCTest.Add(AntecedentsFCR1, ConsequentsFCR1);
        RuleListFCTest.Add(AntecedentsFCR2, ConsequentsFCR2);
        //RuleListFCTest.Add(AntecedentsFCR3, ConsequentsFCR3);
        //RuleListFCTest.Add(AntecedentsFCR4, ConsequentsFCR4);

        RuleListBCTest.Add(AntecedentsBCR1, ConsequentsBCR1);
        RuleListBCTest.Add(AntecedentsBCR2, ConsequentsBCR2);
        //RuleListBCTest.Add(AntecedentsBCR3, ConsequentsBCR3);
        //RuleListBCTest.Add(AntecedentsBCR4, ConsequentsBCR4);

        IDictionaryEnumerator _enumerator = RuleListFCTest.GetEnumerator();

        //Perform Forward Chaining to Validate Rule
        return BRForwardChainingEvaluate(FactListFCTest, RuleListFCTest);

        //Perform Backward Chaining to Validate Rule
        //return BRBackwardChainingExample(FactListBCTest, RuleListBCTest);

    }

    //Examine GA Search Results Fact Against Business Rules
    public bool ExamineBusinessRulesSearchResults(Fact validateFact)
    {

        ValidateFact = validateFact;      //Define Fact to be Validated
        //This fact is of type object, which means it can be anything (i.e. strings, int, ... etc)

        FactListFCTest.Add(ValidateFact);                         // ValidateFact is a fact
        FactListBCTest.AddRange(FactListFCTest);

        //Define Rules - Antecedents and Consequents
        ArrayList AntecedentsFCR1 = new ArrayList();
        ArrayList ConsequentsFCR1 = new ArrayList();
        ArrayList AntecedentsFCR2 = new ArrayList();
        ArrayList ConsequentsFCR2 = new ArrayList();
        ArrayList AntecedentsFCR3 = new ArrayList();
        ArrayList ConsequentsFCR3 = new ArrayList();
        ArrayList AntecedentsFCR4 = new ArrayList();
        ArrayList ConsequentsFCR4 = new ArrayList();
        ArrayList AntecedentsBCR1 = new ArrayList();
        ArrayList ConsequentsBCR1 = new ArrayList();
        ArrayList AntecedentsBCR2 = new ArrayList();
        ArrayList ConsequentsBCR2 = new ArrayList();
        ArrayList AntecedentsBCR3 = new ArrayList();
        ArrayList ConsequentsBCR3 = new ArrayList();
        ArrayList AntecedentsBCR4 = new ArrayList();
        ArrayList ConsequentsBCR4 = new ArrayList();
        Hashtable RuleListFCTest = new Hashtable();      //HashTable contains Rule List for Forward Chaining Test
        Hashtable RuleListBCTest = new Hashtable();      //HashTable contains Rule List for Backward Chaining Test

        //Business Rule #1
        AntecedentsFCR1.Add(ValidateFact.SimilarityScore > 0.89);
        ConsequentsFCR1.Add(ValidateFact);
        AntecedentsBCR1.AddRange(AntecedentsFCR1);
        ConsequentsBCR1.AddRange(ConsequentsFCR1);

        //Business Rule #2 - Mutation
        AntecedentsFCR2.Add(ValidateFact.RandomScore > MutateScore);    //Happens 10% of the time
        ConsequentsFCR2.Add(ValidateFact);
        AntecedentsBCR2.AddRange(AntecedentsFCR2);
        ConsequentsBCR2.AddRange(ConsequentsFCR2);

        //Add Each Business Rule to the RuleList HashTables
        RuleListFCTest.Add(AntecedentsFCR1, ConsequentsFCR1);
        RuleListFCTest.Add(AntecedentsFCR2, ConsequentsFCR2);

        RuleListBCTest.Add(AntecedentsBCR1, ConsequentsBCR1);
        RuleListBCTest.Add(AntecedentsBCR2, ConsequentsBCR2);

        IDictionaryEnumerator _enumerator = RuleListFCTest.GetEnumerator();

        //Perform Forward Chaining to Validate Rule
        return BRForwardChainingEvaluate(FactListFCTest, RuleListFCTest);

        //Perform Backward Chaining to Validate Rule
        //return BRBackwardChainingExample(FactListBCTest, RuleListBCTest);

    }

    //Method to initiate Forward Chaining
    public bool BRForwardChainingEvaluate(ArrayList factList, Hashtable ruleList)
    {
        //Forward Chaining
        BRForwardChaining forwardChaining = new BRForwardChaining(factList, ruleList);

        //Console.Write("\n\nEstablish Validity of \"" + ValidateFact.Name + "\" using Business Rules Forward Chaining: ");

        //Validate Rule using Forward Chaining
        if (forwardChaining.forwardChain(ValidateFact))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double GetSimilarityScore(string text1, string text2)
    {
        //Wnlib.WNCommon.path = "C:\\Program Files\\WordNet\\2.1\\dict\\";
        Wnlib.WNCommon.path = "C:\\Inetpub\\wwwroot\\kme_v2\\WordNet\\2.1\\dict\\";

        double score = 0;
        score = semsim.GetScore(text1, text2);
        //score = semsim.GetScore("How Firefly audio JRE is supported", "firefly how technical support");
        return score;
    }
}
