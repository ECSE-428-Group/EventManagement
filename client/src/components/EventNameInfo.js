import React, { useState } from 'react';

// MUI
import {
    Card,
    CardMedia,
    List,
    ListItem,
    ListItemText,
    CardContent,
} from '@material-ui/core';

export default function EventNameInfo() {
    const items = ['Event Name', 'Event Location', 'Event Tags'];

    const listItems = items.map((item, idx) => {
        return (
            <ListItem
                style={{ borderTop: idx !== 0 ? '1px solid #c4c4c4' : 'None' }}
            >
                <ListItemText primary={`${item}`} />
            </ListItem>
        );
    });
    return (
        <Card style={{ backgroundColor: '#f8fcff', borderRadius: '0%' }}>
            <CardMedia
                style={{
                    borderRadius: '50%',
                    objectFit: 'fill',
                    height: '200px',
                    width: '200px',
                }}
                component='img'
                image={require('../static/images/20-93633.jpeg')}
                alt='Tokyo'
            />
            <CardContent>
                <List
                    sx={{
                        width: '100%',
                        maxWidth: 450,
                        bgcolor: 'background.paper',
                    }}
                >
                    {listItems}
                </List>
            </CardContent>
        </Card>
    );
}
