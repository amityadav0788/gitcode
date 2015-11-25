<?php

	class Stack {
		private $_stack = array();
		public function size() {()
			return count($this->_stack);
		}
		public function peek() {
			return end($this->_stack);
		}
		public function push($value = NULL) {
			array_push($this->_stack, $value);
		}
		public function pop() {
			return array_pop($this->_stack);
		}
		public function isEmpty() {
			return empty($this->_stack);
		}
	}
	
	public class TSPNearestNeighbour {
	
		private $numberOfNodes;

        private stack = new Stack;
		
		public function tsp($adjacencyMatrix)
		{
			$numberOfNodes = count($adjacencyMatrix[0])-1;
			$visited = array_fill(0, $numberOfNodes+1, 0);
			$visited[1] = 1;
			$stack->push(1);
			$dst=0;
			$min=PHP_INT_MAX ;
			$minflag = False;
			
			while(!stack->isEmpty())
			{
				$element = $stack->peek();
				$i=1;
				$min=PHP_INT_MAX;
				
				while($i <= $numberOfNodes )
				{
					if($adjacencyMatrix[$element][$i]>=1 and $visited[$i]==0)
					{
					
						if ($min > $adjacencyMatrix[$element][$i])

						{

							$min = $adjacencyMatrix[$element][$i];

							$dst = $i;

							$minFlag = True;

						}
					}
					$i=$+1;
				}
				
				if ($minFlag)

                {

                    $visited[$dst] = 1;

                    $stack->push($dst);
					
					echo $dst +'\t';

                    $minFlag = False;

                    continue;

                }

                $stack->pop();
			}
			
		}
	}
	
	echo 'the citys are visited as follows- <br/>' 
	$tspNearestNeighbour = new TSPNearestNeighbour;
	$matrix = array
				(
					array(1,1,0,0,1,0),
					array(1,0,1,0,1,0),
					array(0,1,0,1,0,0),
					array(0,0,1,0,1,1),
					array(1,1,0,1,0,0),
					array(0,0,0,1,0,0)
				);
	
	$tspNearestNeighbour->tsp($matrix);
?>
	
	