Não foi declarado o pacote da classe

campos do tipo constante (final) devem ter, normalmente, a declaração com letras maiúsculas (static final String umoNota)

os atributos de uma classe devem ter o escopo do tipo privado (String texto;)

Na interface do tipo List deve ser definido o tipo de classe no campo Generics, assim ajudará no entendimento do valor esperado como parâmetro

Atribuir nomes de variáveis mais intuitivas para facilitar futuras manutenções: public String geraObservacao(List lista)

A variavel String texto; deve ser declarada no escopo de cada método, por não ter utilidade como escopo de objeto

Deve ser validado se um determinado objeto é nulo: if (!lista.isEmpty())  

Em java 8 pode ser utilizada a função stream().forEach com o recurso lambda pra definir a função callback que irá iterar nos objetos da lista
  --for (Iterator<Integer> iterator = lista.iterator(); iterator.hasNext();) 
  
Utilizar Long no lugar de Integer para ampliar a quantidade de notas ficais do sistema
  
Comprimento de uma String nunca é menor que zero: cod.toString().length()

Utilizar identação para alinhar os comandos relacionados:
	if( cod.toString() == null || cod.toString().length() <= 0 )
				s =  "";
				else if( iterator.hasNext() )
					s =  ", ";
				else
					s =  " e ";
					
Interessante seria ordenar por código do item para uma melhor visualização do relatório