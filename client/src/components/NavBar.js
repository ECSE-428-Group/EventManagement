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
            name: 'Create Event',
            route: '/createEvent',
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
                        <Typography
                            variant='h4'
                            display='inline'
                            style={{ color: '#ffffff' }}
                        >
                            JoinIt
                        </Typography>
                    </div>
                    <div style={{ padding: 15 }}>
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
