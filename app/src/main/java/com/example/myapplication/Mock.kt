package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.ui.graphics.vector.ImageVector

object Mock {

    val mockAvailableVehicles = listOf(
        AvailableVehicle(
            title = "Ocean freight",
            "International",
            drawable = R.drawable.ocean_freight
        ),
        AvailableVehicle(
            title = "Cargo freight",
            "Reliable",
            drawable = R.drawable.cargo_freight
        ),
        AvailableVehicle(
            title = "Air freight",
            "International",
            drawable = R.drawable.air_freight
        )
    )

    val searchItems = listOf(
        SearchItemModel(
            title = "Macbook pro M2",
            idNumber = "#NE43857340857904",
            from = "Abuja",
            to = "Lagos"
        ),
        SearchItemModel(
            title = "Pixel 7a",
            idNumber = "#NE43857340854444",
            from = "Abuja",
            to = "Lagos"
        ),
        SearchItemModel(
            title = "Pixel 8",
            idNumber = "#NE43857340851254",
            from = "Kaduna",
            to = "Abuja"
        ),
        SearchItemModel(
            title = "Pixel 8 pro",
            idNumber = "#NE43857340859038",
            from = "Kano",
            to = "Abuja"
        ),
        SearchItemModel(
            title = "Pixel 8a",
            idNumber = "#NE43857340852356",
            from = "Ibadan",
            to = "Lagos"
        ),
        SearchItemModel(
            title = "Macbook pro M1",
            idNumber = "#NE43857340856094",
            from = "Lagos",
            to = "Abuja"
        )
    )

    val searchedItems = listOf(
        SearchItemModel(
            title = "Macbook pro M2",
            idNumber = "#NE43857340857904",
            from = "Abuja",
            to = "Lagos"
        ),
        SearchItemModel(
            title = "Pixel 7a",
            idNumber = "#NE43857340854444",
            from = "Abuja",
            to = "Lagos"
        )
    )

    val items = listOf(
        BottomNavigationItemModel(
            title = "Home",
            selectedIcon = Icons.Outlined.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavigationItemModel(
            title = "Calculate",
            selectedIcon = Icons.Outlined.Calculate,
            unselectedIcon = Icons.Outlined.Calculate
        ),
        BottomNavigationItemModel(
            title = "Shipment",
            selectedIcon = Icons.Outlined.Restore,
            unselectedIcon = Icons.Outlined.Restore,
        ),
        BottomNavigationItemModel(
            title = "Profile",
            selectedIcon = Icons.Outlined.Person,
            unselectedIcon = Icons.Outlined.Person,
        )
    )

    val tabItems = listOf(
        TabItem(
            "All",
            12
        ),
        TabItem(
            "Completed",
            5
        ),
        TabItem(
            "In progress",
            3
        ),
        TabItem(
            "Pending",
            2
        ),
        TabItem(
            "Loading",
            2
        )
    )

    val shipments = listOf(
        ShipmentModel(
            status = ShipmentStatus.IN_PROGRESS,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$1400 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.PENDING,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$700 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.PENDING,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$1100 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.LOADING,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$1200 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.COMPLETED,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$400 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.COMPLETED,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$650 USD",
            date = "Sep 20, 2023"
        ),
    )

    val completed = listOf(
        ShipmentModel(
            status = ShipmentStatus.COMPLETED,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$400 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.COMPLETED,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$650 USD",
            date = "Sep 20, 2023"
        ),
    )

    val inProgress = listOf(
        ShipmentModel(
            status = ShipmentStatus.IN_PROGRESS,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$1400 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.IN_PROGRESS,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$400 USD",
            date = "Sep 20, 2023"
        )
    )

    val pending = listOf(
        ShipmentModel(
            status = ShipmentStatus.PENDING,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$700 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.PENDING,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$1100 USD",
            date = "Sep 20, 2023"
        )
    )

    val loading = listOf(
        ShipmentModel(
            status = ShipmentStatus.LOADING,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$1200 USD",
            date = "Sep 20, 2023"
        ),
        ShipmentModel(
            status = ShipmentStatus.LOADING,
            title = "Arriving today!",
            details = "Your delivery, #NE43857340856094",
            subDetails = "from Atlanta, is arriving today!",
            amount = "$400 USD",
            date = "Sep 20, 2023"
        )
    )

    val categories = listOf(
        "Documents",
        "Glass",
        "Liquid",
        "Food",
        "Electronics",
        "Product",
        "Others"
    )
}

data class AvailableVehicle(
    val title: String,
    val subTitle: String,
    @DrawableRes val drawable: Int
    )

data class BottomNavigationItemModel(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

data class SearchItemModel(
    val title: String,
    val idNumber: String,
    val from: String,
    val to: String
)

data class TabItem(
    val title: String,
    val count: Int
)

data class ShipmentModel(
    val status: ShipmentStatus,
    val title: String,
    val details: String,
    val subDetails: String,
    val amount: String,
    val date: String
)

enum class ShipmentStatus {
    IN_PROGRESS,
    PENDING,
    LOADING,
    COMPLETED
}