# Genetic Algorithm for Classification                                                                         
# by Mathieu Belzile-Ha

      CONTENT:
      --------
              - Overview
              - Experiment
              
--------------------------------------------------------------------------------------------------------------------------------
              
      OVERVIEW:
      ---------
              
	      The following was written as part of a project to study the performance of a machine learning program. This
	      program is an AI that learns to classify objects in reference to numerical representations of their
	      attributes. The code was written in reference to standard Genetic Algorithms.
 
	      Saying the AI "learns" in this case might not be the best metaphor. We might instead say that the AI is "grown"
	      in an Incubator.
		  
	      The way the AI is grown is by putting a list of examples of numerical values and what category the thing
	      they belong to should fall in.
			  
	      The Incubator, in the first generation, generates a bunch of AIs at random.
			  
	      Then, for each generation, the AIs attempt to classify the objects given the numerical inputs. The highest
	      performing ones will usually have their data intermixed into two new AIs. These two AIs are usually 
	      aslo mutated at random.
			  
	      This process is repeated until the highest performing AI is of satisfactory performance.
			  
	      For this project, two Incubators were built: the "Standard" Incubator and the 'Reroll" Incubator:
			  
                        Standard Incubator:
                        -------------------
		      			The Standard Incubator grows the AI in standard Genetic Algorithm tradition.
					That is to say, it first generates a population at random, it tests their
					performance, intermixes the data of the two highest performing AIs and
					mutates them a little, and creates a new generation of AIs where the
					population is the same only with the two highest performers replaced with
					their children.
			
                        Reroll Incubator:
                        -----------------
					The Reroll Incubator grows the AI exactly the same as the Standard Incubator except
					that, at every generation, after mixing the algorithms of the two highest performing
					AIs, it will also discard the two lowest performing AIs and replace them with newly
					randomly generated AIs.
              
--------------------------------------------------------------------------------------------------------------------------------

      EXPERIMENT:
      -----------
      		
		How to run:
		-----------
			To run the experiment, run the main method from the "Experiment" class in "src/Experiment/".
			
		What the experiment does:
		-------------------------
			
			The experiment is devided into 7 rounds, each with unique settings applied to the Incubators
			so to test their performance in different circumstances.
								
					- Round 1 is the control round with standard settings.
								
					- Round 2 has a medium mutation rate. A mutation rate is a random modification that is
					  applied to children AI. This is used to inject new properties into the next generation
					  and avoid stagnation.
									  
					- Round 3 has a high mutation rate.
									
					- Round 4 needs to be prefaced with the fact that the AIs do their decision making through
					  decision trees. Generation of random AIs abide by a leaf cap for these decision trees.
					  Round 4 enforces a small limit of leaves on decision trees.
									  
					- Round 5 has a large leaf limit for decision trees.
									
					- Round 6 enforces a small AI population for each generation
									
					- Round 7 enforces a large AI population for each generation.
								
			At every experiment round, AIs are grown in the Standard Incubator and the Reroll Incubator
			for 5 different data sets:
								
					1. Iris: Iris is the control in most respects, other than it is one of the smallest data sets.
									
					2. Iris Large: The same as Iris only with a lot of data.
									
					3. Glass: Glass is the same size as Iris, only its data has a lot of different attributes
					   That the AI will have to keep track of.
									
					4&5. Spiral & Bimodal: These two data sets are very difficult for AIs to learn and are not
					     included in the experiment with anticipation that the AI will succeed in learning
					     them. They are only there to test their limits.
										
			For each of these rounds and each of these data sets and each Incubator, 3 performance variables are recorded:
			"fitness over time ", "confusion matrix", and "fitness score":
								
					1. Fitness over time: Fitness here can be understood as synonymous to "performance".
					   Fitness over time is bassically measured as the 3 highest fitnesses in an AI population
					   accross generations.
									   
					2. Confusion matrix: The confusion matrix is measured when the Incubator is done growing
					   the AI. It gives the AI a set of questions and writes a "confusion matrix" which is
					   essentially juxtaposition of the expected answer for each question and the answer the AI
					   gave.
									   
					3. Fitness score: The fitness score is measured when the Incubator is done growing the AI.
					   It is bassically just the fitness of the final AI represented as a percentage of answers it
					   got right when asked to classify data.
					  
			These are recorded in individual text files "fitness.txt", "confusion.txt" and, "score.txt" which can be
			found can be found in "src\Experiment\results<#1>\<#2>\<#3>\" Where <#1> is the number of the round the 
			results are for, <#2> the name of the data set, and <#3> is the incubator type.
			
			The format of these files is explained below.
			
			
		Performance Files Format:
		-------------------------
		
												Performance data is recorded in a set of files that can be found in
									
											"src\Experiment\results<#1>\<#2>\<#3>\"
											
									Where <#1> is the number of the round the results are for.
										  <#2> is "bimodal", "glass", "iris", "irisLarge", or "spiral" for what data set
						                       the results are for.
										  <#3> is "basicIncubator" or "rerollIncubator" for which incubator the results
										       are for.
											   
									In this folder, there will be 3 files:
									
														- "confusion.txt" : This contains a column of comma seperated numbers. 
																			These numbers are representative of categories.
																			
																			The numbers to the right are the expected answers
																			to the left are the AI's answers.
																			
																			ex:		
																					AI answer	Excpected answer
																					---------   ----------------
																					   ||			  ||
																					   \/			  \/
																					   
																					    1     ,       3
																						2     ,       2
																						4     ,       4
																						3     ,       1
																						
																						.
																						.
																						.
																						
														- "fitnes.txt" :	This contains a column of 4 comma seperated numbers.
																			The first is the generation number, the three others
																			are the top 3 fitnesses in that given generation.
																			
														- "score.txt" :		This contains the fitness score of the final generated
																			AI.

			
		
		Observation notes:
		------------------
		
			Here is a summary of what I have observed so far:
      
		I have left the Experiment class so that those who are curious may witness the AI at work for themselves.
				Be advised that the experiment can take over an hour to complete. A FAQ is provided bellow explaining how to
				run the experiment and what the output data means.
				
				For those who do not want to spend that time, here is a brief synopsis of what results the experiment yielded:
				
						It looks like the Reroll Incubator is in part an improvement on the Standard Incubator.
						
						A benefit is that, in cases where all AIs in a generation are performing poorly, the Reroll Incubator's
						higher entropy comes in handy as it allows for rapid change as opposed to the Standard Incubator that
						relies on only mutations. The result is visible when we look at the fitness over time data as AIs
						grown in the Reroll Incubator as these score can rapidly fluxtuate.
						
						The downfall of Reroll is now evident as this fluctuation can also work against it. In theory, we would
						think that, since only the lowest performing AIs would be replaced with a randomly generated AI, it
						could not harm the fitness of its population since its best algorithms will not be touched. However,
						should a mutation occur which significantly reduces the highest performing AI's performance, it will
						be removed in the next generation. The reason this can be bad is that, even poorly performing AIs may
						have parts of their algorithms that would be very beneficial when intermixed with other strong AIs'
						algorithms. This likelyhood is increased when the poorly performing AI was once the highest performing.
						
						We see this happen when we track the fitness over time for the Reroll Incubator. Sometimes, the final AI
						produced has a fitness score of around 0.33, whereas durring incubation, the three highest performing AIs
						had scores of around 0.8.
						
						When looking at the fitness over time of the Standard Incubator, it is much more stable and progressive.
						It worth noting that, while the Reroll Incubator successfully grows the AI more often, inreference to
						the fitness over time, it often seems to do so "out of the blue". I say this because the top fitnesss
						scores before it are sometimes very low, if not fluctuating in a way that is not very discerneable from
						the rest of the fluctuations. It gives the impression that sometimes the Reroll Incubator just works out
						of luck. This might not be entirely the case as surely worthy algorithm pieces are preserved, though it
						I get the impression that the difference boils down to the Standard Incubator getting one "dice throw"
						and the Reroll Incubator getting many, so to speak.
						
						In cases where leaf counts are smaller, the confusion matrix also show a greater ease for AIs grown with
						the Reroll Incubator to make complex assessments.
						
						Reroll Incubators also seem to fair better with higher mutation than its Standard counter part. This might
						be because, given a bad mutation, it can easily replace it, whereas the Standard Incubator is stuck with it.
						However, it is interesting that more, or rather, a different kind of entropy helps the Incubator deal with
						mutation entropy. It suggests that kinds of entropies need to be considered as opposed to simply "entropy"
						when designing an incubator.
						
						The conclusion I draw from these results is that the Reroll Incubator shows improvements in some specific
						facets of the learning process. That being said, it may benefit from the progressive aspects of the
						Standard Incubator. The reason is that, should it never reach the fitness score that ends incubation, it
						may loose an AI that had a high fitness none the less. A potential remedy would be to try to make an
						incubator that works at keeping the hight performing AIs as backup and only replace them if newly generated
						ones are higher in performance. Moreover, perhaps some way of having an AI's ancestry affect its elimination
						would also be helpful. Or perhaps assessment not only of the whole of its algorithm but also parts of it.
						
						In regards to the structure of the decision trees, I think these too played a role in the AIs' performances.
						The decision trees are only built to consider ">", "<", or "=" relationships. Data sets like the Spiral data
						- and potentially the glass data set - I suspect need ratio relationships for the AI to properly reason
						around them. As such, the tree would have to be updated for this and potentially other multi-dimensional
						relationships.
						
				
				Experiment FAQ:
				---------------
				
					What does the experiment do?
					----------------------------
					
								The experiment is devided into 7 rounds, each with unique settings applied to the Incubators
								so to test their performance in different circumstances.
								
									- Round 1 is the control round with standard settings.
								
									- Round 2 has a medium mutation rate. A mutation rate is a random modification that is
									  applied to children AI. This is used to inject new properties into the next generation
									  and avoid stagnation.
									  
									- Round 3 has a high mutation rate.
									
									- Round 4 needs to be prefaced with the fact that the AIs do their decision making through
									  decision trees. Generation of random AIs abide by a leaf cap for these decision trees.
									  Round 4 enforces a small limit of leaves on decision trees.
									  
									- Round 5 has a large leaf limit for decision trees.
									
									- Round 6 enforces a small AI population for each generation
									
									- Round 7 enforces a large AI population for each generation.
								
								At every experiment round, AIs are grown in the Standard Incubator and the Reroll Incubator
								for 5 different data sets:
								
									1. Iris: Iris is the control in most respects, other than it is one of the smallest data sets.
									
									2. Iris Large: The same as Iris only with a lot of data.
									
									3. Glass: Glass is the same size as Iris, only its data has a lot of different attributes
									   That the AI will have to keep track of.
									
									4&5. Spiral & Bimodal: These two data sets are very difficult for AIs to learn and are not
										included in the experiment with anticipation that the AI will succeed in learning
										them. They are only there to test their limits.
										
								For each data set, 3 performance attributes are recorded for each Incubator:
								
									1. Fitness over time: Fitness here can be understood as synonymous to "performance".
									   Fitness over time is bassically measured as the 3 highest fitnesses in an AI population
									   accross generations.
									   
									2. Confusion matrix: The confusion matrix is measured when the Incubator is done growing
									   the AI. It gives the AI a set of questions and writes a "confusion matrix" which is assentially
									   juxtaposition of the expected answer for each question and the answer the AI gave.
									   
									3. Fitness score: The fitness score is measured when the Incubator is done growing the AI.
									   It is bassically just the fitness of the final AI represented as a percentage of answers it
									   got right when asked to classify data.
				
					How do I run the experiment?
					----------------------------
					
								To run the experiment, run the main method from the "Experiment" class in "src/Experiment/".
				
					I ran the experiment, where do I find the results? What do they mean?
					---------------------------------------------------------------------
								
								When the experiment is run, messages will be output to the console and data will be written
								to files. This section is divided into explanations for the CONSOLE OUTPUT and for the FILE
								OUTPUT.
								
								CONSOLE OUTPUT:
								---------------
									
									When the experiment is run, it will output to console "EXPERIMENT #" where # is the number of 
									the experiment round it is at.
									
									For each round, it will output "DATA SET: #" where # is the name of the data 
									set the AI is learning from.
									
									For each data set, it will output "FITNESS: #", "CONFUSION: #", "SCORING: #", where
									# is "SI" or "RI" for "Standard Incubator" or "Reroll Incubator".
									
								FILE OUTPUT:
								------------
								
									Performance data is recorded in a set of files that can be found in
									
											"src\Experiment\results<#1>\<#2>\<#3>\"
											
									Where <#1> is the number of the round the results are for.
										  <#2> is "bimodal", "glass", "iris", "irisLarge", or "spiral" for what data set
						                       the results are for.
										  <#3> is "basicIncubator" or "rerollIncubator" for which incubator the results
										       are for.
											   
									In this folder, there will be 3 files:
									
														- "confusion.txt" : This contains a column of comma seperated numbers. 
																			These numbers are representative of categories.
																			
																			The numbers to the right are the expected answers
																			to the left are the AI's answers.
																			
																			ex:		
																					AI answer	Excpected answer
																					---------   ----------------
																					   ||			  ||
																					   \/			  \/
																					   
																					    1     ,       3
																						2     ,       2
																						4     ,       4
																						3     ,       1
																						
																						.
																						.
																						.
																						
														- "fitnes.txt" :	This contains a column of 4 comma seperated numbers.
																			The first is the generation number, the three others
																			are the top 3 fitnesses in that given generation.
																			
														- "score.txt" :		This contains the fitness score of the final generated
																			AI.
