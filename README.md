# Moonlay API Assessment

### Teknologi dan library digunakan:
| Teknologi   | Version | Link |
| ----------- | ---------------- | ------------------- |
| Java      | openjdk 17.0.8.1 2023-08-24   |  |
| Java Compiler     | javac 17.0.8.1     |  | 
| Spring | v3.0.12 |  |
| Maven | v3.6.3 | |
| Mysql | v5.7 | |
| Docker | v24.0.6 or later |  |
| Docker compose| v12.21.0 or later |  |
<br>

## To Do
    Install java and Maven
    Install docker dan docker-compose
    Install postman
    Install git
    clone repo [https://github.com/dirgadm/phincon-api.git]

## Ruuning Server
    1. command: *docker compose up -d*, Menjalankan file docker compose di sisi background, sekaligus melakukan migrasi sql ke database mysql, migrasi file terdapat di file *database.sql*
    2. command: *docker compose down*, Menghentikan container docker dan database tidak dapat diakses lagi
    3. command: *mvn spring boot:run* , menjalankan server dengan port 8080
    4. command: *mvn clean install* , Melakukan scan terhadap dependency di dalam project
    ```

## Endpoint Testing 
    1. Untuk kontrak API(Backend - FrontEnd) dapat di lihat di dalam file *.md di dalam folder ./doc/
    2. Untuk testing di postman tersedia di `./doc/Phincon.postman_collection.json` dan siap untuk di import ke Postman
    3. base_url: http://localhost:8080
    

## List Endpoint
1. [METHOD:POST] Register: Melakukan register user dan disimpan di dalam database
2. [METHOD:POST] Login: Melakukan login user dan mengembalikan Token(X-API-TOKEN), dan disimpan dalam database, yang akan digunakan sebagai authentication untuk semua endpoint yang akan di jelaskan setelah point 2 ini

> !!!Warning!!! Token sebagai header dengan key:X-API-TOKEN, MANDATORY untuk semua endpoint yang di jabarkan dari point 3 - seterusnya
3. [METHOD:GET] Get User: mengembalikan informasi user yang sedang login 
4. [METHOD:GET] Get Pokemon List: mengembalikan informasi Pokemon List sesuai dengan instruksi assesstment, data di dapat dari [https://pokeapi.co/]
5. [METHOD:GET] Get Pokemon By ID: mengembalikan informasi Pokemon berdasarkan ID sesuai dengan instruksi assesstment, data di dapat dari [https://pokeapi.co/]
6. [METHOD:PUT] Catch Pokemon: Menangkap pokemon sesuai dengan ID yang diberikan dengan probabilitas 50% keberhasilan dan disimpan di dalam database
7. [METHOD:GET] My Pokemon List: Menampilkan List Pokemon yang sudah ditangkap oleh user
8. [METHOD:DELETE] Release Pokemon: Melepaskan Pokemon dengan ketentuan angka prima(prima: berhasil release|non-prima: gagal release) dan menghapus data pokemon dari user di database
8. [METHOD:PUT] Rename Pokemon: Mengubah nickname pokemon yang sudah ditangkap user dengan suffix -[fibonacci-number]
