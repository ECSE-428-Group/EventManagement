import React, { memo } from 'react';

// Router
import { useNavigate } from 'react-router-dom';

// MUI
import { AppBar, Toolbar, Typography, Button, Hidden } from '@material-ui/core';

function NavBar() {
    const navigate = useNavigate();

    function handleClick(route) {
        if (route !== undefined) navigate(`${route}`);
    }
    const menuItems = [
        {
            name: 'Organize',
        },
        {
            link: './pages/signup',
            name: 'Sign Up',
            route: '/signup',
        },
        {
            link: './pages/signin',
            name: 'Login',
            route: '/signin',
        },
    ];
    return (
        <div>
            <AppBar position='fixed'>
                <Toolbar>
                    <div>
                        <Typography variant='h4' display='inline' color='white'>
                            JoinIt
                        </Typography>
                    </div>
                    <div>
                        <Hidden smDown>
                            {menuItems.map((element) => {
                                return (
                                    <Button
                                        onClick={() =>
                                            handleClick(element.route)
                                        }
                                        size='large'
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
