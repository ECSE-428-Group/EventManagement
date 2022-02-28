import React from 'react';

import CreateEventComponent from '../components/CreateEventComponent';

export default function CreateEvent() {
    const input = {
        data: ['Event Name', 'Location', 'Tags/Interest', 'Other info'],
        label: ['name', 'Location', 'Tags', 'Etc'],
        type: 'Create Event',
        button: 'Create Event',
    };

    const footer = {
        x: 'Cover Photo',
        y: 'Private?',
        a: 'Set Users Anonymous?',
        b: 'Add Organizers',
    };
    return <CreateEventComponent input={input} footer={footer} />;
}
