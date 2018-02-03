gradle mlInit
gradle mlDeploy
mlcp.bat import -host localhost -port 8040 -username admin -password admin -input_file_type documents -document_type json -input_file_path /C:/Learning/Demo/mylibrary/Data/movie -output_uri_replace "/C:/Learning/Demo/mylibrary/Data/,''"