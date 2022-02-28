import * as React from 'react';

// Router
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// Pages
import Signin from './pages/Signin';
import Signup from './pages/Signup';
import EditProfile from './pages/EditProfile';
import UserHome from './pages/UserHome';
import CreateEvent from './pages/CreateEvent';

function App() {
    return (
        <>
            <Router>
                <Routes>
                    <Route exact path='/signup' element={<Signup />} />
                    <Route exact path='/signin' element={<Signin />} />
                    <Route exact path='/userHome' element={<UserHome />} />
                    <Route
                        exact
                        path='/editProfile'
                        element={<EditProfile />}
                    />
                    <Route
                        exact
                        path='/createEvent'
                        element={<CreateEvent />}
                    />
                </Routes>
            </Router>
        </>
    );
}

export default App;
