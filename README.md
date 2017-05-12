# GeneticAlgorithm for classification                                                                         Mathieu Belzile-Ha
                                                             README                                                       
--------------------------------------------------------------------------------------------------------------------------------

      CONTENT:
      -------
              - Overview
              - Experiment
              
--------------------------------------------------------------------------------------------------------------------------------
              
      OVERVIEW:
      --------
              The following is a program designed to learn to classify a given set of objects given examples of these objects
              the attributes of which are represented by numerical data.
              
              The program is implemented with reference to genetic algorithms though some liberties were taken for the sake of
              exploring a GA for building a decision tree for classification, the implementation of which was in part inspired
              by the neural network implementations.
              
              The AI is essentially "grown" using an "Incubator". This incubator decides how the AI grows in repsonse to
              the example data it is fed.
              
              This code actually contains two different incubators: the "Standard" Incubator and the 'Reroll" Incubator.
              
              
                      Standard Incubator:
                      -------------------
                                        For the standard Incubator, 
              
--------------------------------------------------------------------------------------------------------------------------------

      EXPERIMENT:
      ----------
              An Experiment class is included for evaluating the AI's performance under two different learning techniques.