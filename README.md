## Expressions-stream

Динамический парсер формул. Парсинг формул реализован через рефлексию.

### Запуск:
 git clone https://github.com/diiir727/expressions-stream.git  
 cd expression-stream  
 mvn package  
 java -jar target/expressions-1.jar "../expressions-stream/expressions.json" 500  

### Параметры
* expression_file_path - файл с формулами в формате json,
пример есть в корне проекта (expressions.json)
* number_generate_duration - задержка генерации
случайных чисел в миллисекундах.
