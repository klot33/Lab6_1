import kotlin.random.Random

val cities = listOf(
    "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород",
    "Казань", "Челябинск", "Омск", "Самара", "Ростов-на-Дону",
    "Уфа", "Красноярск", "Воронеж", "Пермь", "Волгоград"
)

data class Wagon(val capacity: Int, var passengers: Int = 0)

class Train {
    val wagons = mutableListOf<Wagon>()

    // Добавляем вагон к поезду
    fun addWagon(wagon: Wagon) {
        wagons.add(wagon)
    }

    // Выводим информацию о поезде
    fun printTrainInfo() {
        println("Поезд состоит из ${wagons.size} вагонов.")
        wagons.forEachIndexed { index, wagon ->
            println("Вагон ${index + 1}: вместимость ${wagon.capacity}, пассажиров ${wagon.passengers}")
        }
    }
}

// Функция для создания направления
fun createDirection(): Pair<String, String> {
    val city1 = cities.random()
    var city2 = cities.random()
    while (city1 == city2) {
        city2 = cities.random()
    }
    println("Направление: $city1 - $city2")
    return city1 to city2
}

// Функция для продажи билетов
fun sellTickets(): Int {
    val passengers = Random.nextInt(5, 201)
    println("Количество пассажиров: $passengers")
    return passengers
}

// Функция для формирования поезда
fun formTrain(passengers: Int): Train {
    val train = Train()
    var remainingPassengers = passengers
    println("Формируем поезд...")

    while (remainingPassengers > 0) {
        val wagonCapacity = Random.nextInt(5, 26)
        val passengersInWagon = if (remainingPassengers >= wagonCapacity) wagonCapacity else remainingPassengers
        train.addWagon(Wagon(wagonCapacity, passengersInWagon))
        remainingPassengers -= passengersInWagon
    }

    println("Поезд сформирован.")
    train.printTrainInfo()
    return train
}

fun main() {
    while (true) {
        println("Введите 'EXIT' для выхода или 'START' для составления поезда:")
        when (readLine()?.uppercase()) {
            "EXIT" -> {
                println("Программа завершена.")
                return
            }
            "START" -> {
                // Шаг 1: Создать направление
                val direction = createDirection()

                // Шаг 2: Продать билеты
                val passengers = sellTickets()

                // Шаг 3: Сформировать поезд
                val train = formTrain(passengers)

                // Шаг 4: Отправить поезд
                println("Поезд по направлению ${direction.first} - ${direction.second}, состоящий из ${train.wagons.size} вагонов, отправлен.")
            }
            else -> println("Неверный ввод. Попробуйте снова.")
        }
    }
}