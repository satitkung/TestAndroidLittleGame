package com.example.test.Elevator



class FloorElevatorHolder(private val view: FloorElevatorView): ElevatorBaseHolder(view) {

    override fun bindData(
        floor: Int,
        isCurrentFloor: Boolean,
        isSelectFloor: Boolean,
        onItemSelected: ((Int) -> Unit)?
    ) {
        view.bindData(floor, isCurrentFloor, isSelectFloor, onItemSelected)
    }

}