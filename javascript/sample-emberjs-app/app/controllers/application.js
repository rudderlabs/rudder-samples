/* eslint-disable ember/no-actions-hash */
/* eslint-disable ember/avoid-leaking-state-in-ember-objects */
/* eslint-disable no-unused-vars */
/* eslint-disable ember/new-module-imports */
import Controller from '@ember/controller';
import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addEvent: function () {
      console.log('Clicked');
      window.rudderanalytics.track('RS and Ember demo track event');
    },
  },
});
