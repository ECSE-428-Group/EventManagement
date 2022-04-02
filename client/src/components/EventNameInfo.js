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

export default function EventNameInfo({ eventDetails }) {
    var year = eventDetails.date.slice(0, 4);
    var monthNm = eventDetails.date.slice(5, 7);
    var day = eventDetails.date.slice(8, 10);
    var time = eventDetails.date.slice(11, 16);
    var fullDate =
        'This event is on (dd/mm/yyyy): ' +
        day +
        '/' +
        monthNm +
        '/' +
        year +
        ' at ' +
        time;

    const items = [
        'When and Where is this event?',
        fullDate,
        'This event is taking place at: ' + eventDetails.location,
    ];
    const listItems = items.map((item, idx) => {
        return (
            <ListItem
                key={idx}
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
                    borderRadius: '5%',
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
