import { TestBed } from '@angular/core/testing';

import { KneipenServiceService } from './kneipen-service.service';

describe('KneipenServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KneipenServiceService = TestBed.get(KneipenServiceService);
    expect(service).toBeTruthy();
  });
});
