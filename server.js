const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Connect to MongoDB Atlas
mongoose.connect(process.env.MONGO_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
.then(() => console.log("✅ Connected to MongoDB Atlas"))
.catch(err => console.error("❌ Connection Error:", err));

// Schema (Mongoose will use existing collection if it exists)
const contactSchema = new mongoose.Schema({
    name: String,
    email: String,
    subject: String,
    message: String,
    date: { type: Date, default: Date.now }
});

// Model (collection name: 'contacts')
const Contact = mongoose.model('contacts', contactSchema);

// POST route to save form data
app.post('/contact', async (req, res) => {
    try {
        const newContact = new Contact(req.body);
        await newContact.save();
        res.status(200).json({ message: 'Message saved successfully!' });
    } catch (error) {
        console.error("❌ Error saving message:", error);
        res.status(500).json({ error: 'Failed to save message' });
    }
});

// Test route
app.get('/', (req, res) => {
    res.send("Contact form backend is running!");
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`🚀 Server running on port ${PORT}`));
