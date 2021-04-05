import { TestBed } from '@angular/core/testing';

import { FrageServiceService } from './frage-service.service';

describe('FrageServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FrageServiceService = TestBed.get(FrageServiceService);
    expect(service).toBeTruthy();
  });
});
