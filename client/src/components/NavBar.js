import React, { memo } from "react";

import {
    AppBar,
    Toolbar,
    Typography,
    Button,
    Hidden,
} from "@material-ui/core";

function NavBar() {
    const menuItems = [
        {
            name: "Organize",
        },
        {
            link: "./pages/signup",
            name: "Sign Up",
        },
        {
            link: "./pages/signin",
            name: "Login",
        }
    ];
    return (
        <div>
            <AppBar position="fixed">
                <Toolbar >
                    <div>
                        <Typography
                            variant="h4"
                            display="inline"
                            color="white"
                        >
                            JoinIt
                        </Typography>
                    </div>
                    <div>
                        <Hidden smDown>
                            {menuItems.map(element => {
                                return (
                                    <Button
                                        size="large"
                                        key={element.name}
                                    >
                                        {element.name}
                                    </Button>
                                );
                            })}
                        </Hidden>
                    </div>
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default NavBar;