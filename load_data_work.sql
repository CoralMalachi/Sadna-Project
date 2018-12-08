CREATE TABLE instrument ( 
  id int(11)  NOT NULL AUTO_INCREMENT,
  type VARCHAR( 45 ) , 
  KEY (id)                        
); -- ENGINE = MYISAM;

LOAD DATA LOCAL INFILE '/home/coral1219/Downloads/instrument' INTO TABLE `new_schema`.instrument;