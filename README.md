# Genetic Algorithm for Classification                                                                         
# by Mathieu Belzile-Ha

      CONTENT:
      -------
              - Overview
              - Experiment
              
--------------------------------------------------------------------------------------------------------------------------------
              
      OVERVIEW:
      --------
              The following is an AI that learns to classify a given set of objects given examples of these objects
              the attributes of which are represented by numerical data. This was written as part of a project to explore
              various implementations of machine learning.
              
              The program is implemented with reference to genetic algorithms though some liberties were taken for the sake of
              exploring a GA for building a decision tree for classification, the implementation of which was in part inspired
              by the neural network implementations.
              
              The AI is essentially "grown" using an "Incubator". This incubator decides how the AI grows in repsonse to
              the example data it is fed.
              
              The code contains two different incubators: the "Standard" Incubator and the 'Reroll" Incubator.
              
                      Standard Incubator:
                      -------------------
                                        For the standard Incubator, 
              
--------------------------------------------------------------------------------------------------------------------------------

      EXPERIMENT:
      ----------
              An Experiment class is included for evaluating the AI's performance under two different learning techniques.
